package com.yamlquest.parser.util;
import com.yamlquest.parser.*;
import com.yamlquest.parser.exception.NoRequestsFoundException;
import java.util.HashMap;

public class ParserValidator {

    public static ParseOutput validate(ParseInput parseInput){
        if (parseInput.getRequests().isEmpty()){
            throw new NoRequestsFoundException("No request(s) found on the yaml file");
        }

        int index = 0;
        HashMap<Integer, RequestDetails> requestMap = new HashMap<>();
        for (Request request:parseInput.getRequests()){
            requestMap.put(index,validateRequest(request,index));
            index++;
        }
        return new ParseOutput(requestMap,index);
    }

    private static RequestDetails validateRequest(Request request, int requestIndex){
        RequestDetails details = new RequestDetails();
        if (request.getName().isEmpty() || request.getName().isBlank()){
            details.setIssue(String.format("The request at position %s is missing name field",requestIndex));
            return details;
        }

        if (request.getMethod().isEmpty() || request.getMethod().isBlank()){
            details.setIssue(String.format("The request at position %s is missing method field",requestIndex));
            return details;
        }

        if (!RequestMethods.isMethodSupported(request.getMethod())){
            details.setIssue(String.format("The request at position %s has unsupported method",requestIndex));
            return details;
        }

        details.setRequest(request);
        return details;
    }
}
