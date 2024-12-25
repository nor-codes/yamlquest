package com.yamlquest.http_client;
import okhttp3.*;
import java.io.IOException;
/**
 * Author: Nonelela Cele
 * Version: 1.0
 * Date: 2024-12-22
 */
public class RequestClient {

    private Request buildRequest(RequestInfo requestInfo){
        Request.Builder requestBuilder = new Request
                .Builder()
                .url(requestInfo.getUrl());

        if (requestInfo.getHeaders()!=null  && !requestInfo.getHeaders().isEmpty()){
            Headers headers = Headers.of(requestInfo.getHeaders());
            requestBuilder.headers(headers);
        }
        RequestBody requestBody = createRequestBody(requestInfo);
        requestBuilder.method(requestInfo.getMethod(),requestBody);
        return requestBuilder.build();
    }

    private RequestBody createRequestBody(RequestInfo requestInfo){
        MediaType mediaType = MediaType.get(requestInfo.getContentType());
        if (requestInfo.getRequestBody()!=null){
            return RequestBody.create(requestInfo.getRequestBody(),mediaType);
        }
        return null;
    }

    public ClientResponse call(RequestInfo requestInfo) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ClientResponse clientResponse = new ClientResponse();
        Request request = buildRequest(requestInfo);

        long startTime = System.currentTimeMillis();
        try (Response response = client.newCall(request).execute()) {
            long endTime = System.currentTimeMillis();
            clientResponse.setStatusCode(response.code());
            clientResponse.setSuccess(response.isSuccessful());
            clientResponse.setStatusMessage(response.message());
            clientResponse.setResponseBody(response.body() != null ? response.body().string() : null);
            clientResponse.setHeaders(response.headers().toMultimap());
            clientResponse.setResponseTime(endTime - startTime);
            clientResponse.setRequestTitle(requestInfo.getName());
        }
        return clientResponse;
    }

}
