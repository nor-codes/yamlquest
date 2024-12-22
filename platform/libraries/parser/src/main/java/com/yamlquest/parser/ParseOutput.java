package com.yamlquest.parser;

import java.util.HashMap;

public class ParseOutput {

    HashMap<Integer,RequestDetails> requestHashMap;
    private int numberOfRequests;

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public void setNumberOfRequests(int numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }

    public ParseOutput(HashMap<Integer, RequestDetails> requestHashMap, int numberOfRequests) {
        this.requestHashMap = requestHashMap;
        this.numberOfRequests = numberOfRequests;
    }

    public HashMap<Integer, RequestDetails> getRequestHashMap() {
        return requestHashMap;
    }

    public void setRequestHashMap(HashMap<Integer, RequestDetails> requestHashMap) {
        this.requestHashMap = requestHashMap;
    }
}
