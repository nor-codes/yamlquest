package com.yamlquest.parser;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
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
