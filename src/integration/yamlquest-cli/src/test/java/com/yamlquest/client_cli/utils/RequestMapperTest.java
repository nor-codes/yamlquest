package com.yamlquest.client_cli.utils;

import com.yamlquest.http_client.RequestInfo;
import com.yamlquest.parser.ParseOutput;
import com.yamlquest.parser.Request;
import com.yamlquest.parser.RequestDetails;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestMapperTest {
    @Test
    void testMapParseOutputToRequestInfo_withValidData() {
        // Arrange
        Request request = new Request();
        request.setName("Get Users");
        request.setMethod("GET");
        request.setUrl("https://api.example.com/users");
        request.setHeaders(Map.of("Authorization", "Bearer token"));
        request.setQueryParams(Map.of("page", "1"));
        request.setContentType("application/json");
        request.setRequestBody(null);

        RequestDetails requestDetails = new RequestDetails();
        requestDetails.setRequest(request);

        Map<Integer, RequestDetails> requestMap = new HashMap<>();
        requestMap.put(1, requestDetails);

        ParseOutput parseOutput = new ParseOutput(requestMap, 1);

        // Act
        List<RequestInfo> result = RequestMapper.mapParseOutputToRequestInfo(parseOutput);

        // Assert
        assertEquals(1, result.size());
        RequestInfo info = result.get(0);
        assertEquals("Get Users", info.getName());
        assertEquals("GET", info.getMethod());
        assertEquals("https://api.example.com/users", info.getUrl());
        assertEquals("application/json", info.getContentType());
        assertEquals("Bearer token", info.getHeaders().get("Authorization"));
        assertEquals("1", info.getQueryParams().get("page"));
        assertNull(info.getRequestBody());
    }

    @Test
    void testMapParseOutputToRequestInfo_withNullRequests_shouldIgnoreThem() {
        // Arrange
        RequestDetails requestDetails = new RequestDetails(); // request is null
        Map<Integer, RequestDetails> requestMap = Map.of(1, requestDetails);
        ParseOutput parseOutput = new ParseOutput(requestMap, 1);

        // Act
        List<RequestInfo> result = RequestMapper.mapParseOutputToRequestInfo(parseOutput);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testMapParseOutputToRequestInfo_withEmptyMap_returnsEmptyList() {
        // Arrange
        ParseOutput parseOutput = new ParseOutput(Collections.emptyMap(), 0);

        // Act
        List<RequestInfo> result = RequestMapper.mapParseOutputToRequestInfo(parseOutput);

        // Assert
        assertTrue(result.isEmpty());
    }
}