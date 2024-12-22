package com.yamlquest.parser;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/**
 * Author: Nonelela Cele
 * Version: 1.0
 * Date: 2024-12-22
 */
public class ParseInput {
    @JsonProperty("requests")
    private List<Request> requests;

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
