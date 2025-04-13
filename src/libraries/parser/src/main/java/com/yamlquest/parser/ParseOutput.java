package com.yamlquest.parser;
import java.util.Map;

/**
 * Author: Nonelela Cele
 * Version: 1.0
 * Date: 2024-12-22
 */
public class ParseOutput {

    Map<Integer,RequestDetails> requestHashMap;
    private int numberOfRequests;

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public void setNumberOfRequests(int numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }

    public ParseOutput(Map<Integer, RequestDetails> requestHashMap, int numberOfRequests) {
        this.requestHashMap = requestHashMap;
        this.numberOfRequests = numberOfRequests;
    }

    public Map<Integer, RequestDetails> getRequestHashMap() {
        return requestHashMap;
    }

    public void setRequestHashMap(Map<Integer, RequestDetails> requestHashMap) {
        this.requestHashMap = requestHashMap;
    }
}
