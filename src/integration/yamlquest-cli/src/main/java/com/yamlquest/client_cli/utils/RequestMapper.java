package com.yamlquest.client_cli.utils;

import com.yamlquest.http_client.RequestInfo;
import com.yamlquest.parser.ParseOutput;
import com.yamlquest.parser.Request;
import com.yamlquest.parser.RequestDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestMapper {

    public static List<RequestInfo> mapParseOutputToRequestInfo(ParseOutput parseOutput) {
        List<RequestInfo> requestInfoList = new ArrayList<>();

        HashMap<Integer, RequestDetails> requestDetailsMap = parseOutput.getRequestHashMap();

        for (Map.Entry<Integer, RequestDetails> entry : requestDetailsMap.entrySet()) {
            RequestDetails requestDetails = entry.getValue();
            Request request = requestDetails.getRequest();

            if (request != null) {
                RequestInfo requestInfo = getRequestInfo(request);

                requestInfoList.add(requestInfo);
            } else {
                System.err.println("RequestDetails at key " + entry.getKey() + " is missing a Request object.");
            }
        }

        return requestInfoList;
    }

    private static RequestInfo getRequestInfo(Request request) {
        RequestInfo requestInfo = new RequestInfo();
        // Mapping fields from Request to RequestInfo
        requestInfo.setName(request.getName());
        requestInfo.setMethod(request.getMethod());
        requestInfo.setUrl(request.getUrl());
        requestInfo.setHeaders(request.getHeaders());
        requestInfo.setQueryParams(request.getQueryParams());
        requestInfo.setContentType(request.getContentType());
        requestInfo.setRequestBody(request.getRequestBody());
        return requestInfo;
    }
}
