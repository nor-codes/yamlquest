package com.yamlquest.http_client;
import okhttp3.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class RequestClientTest {
    private RequestClient requestClient;

    @Mock
    private OkHttpClient mockHttpClient;

    @Mock
    private Call mockCall;

    @Mock
    private Response mockResponse;

    @Mock
    private ResponseBody mockResponseBody;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        requestClient = new RequestClient();
    }

    @AfterEach
    void tearDown() {
    }

//    @Test
    public void testCall_SuccessfulResponse() throws IOException {
        // Arrange
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setUrl("https://example.com/api");
        requestInfo.setMethod("GET");
        requestInfo.setHeaders(new HashMap<>());
        requestInfo.setContentType("application/json");

        when(mockHttpClient.newCall(any(Request.class))).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(mockResponse);
        when(mockResponse.code()).thenReturn(200);
        when(mockResponse.message()).thenReturn("OK");
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockResponseBody.string()).thenReturn("{\"success\":true}");

        // Act
        ClientResponse clientResponse = requestClient.call(requestInfo);

        // Assert
        assertEquals(200, clientResponse.getStatusCode());
        assertEquals("OK", clientResponse.getStatusMessage());
        assertEquals("{\"success\":true}", clientResponse.getResponseBody());
    }

}