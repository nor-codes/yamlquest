package com.yamlquest.client_cli.utils;
import com.yamlquest.http_client.ClientResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestWithoutValidationsFormatterTest {
    private final RequestWithoutValidationsFormatter<Object> formatter = new RequestWithoutValidationsFormatter<>();

    @Test
    void testFormat_successfulResponse() {
        ClientResponse response = new ClientResponse();
        response.setStatusCode(200);
        response.setStatusMessage("OK");
        response.setRequestTitle("Fetch User");
        response.setSuccess(true);
        response.setResponseBody("{ \"name\": \"John\" }");

        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Content-Type", List.of("application/json"));
        headers.put("X-Request-Id", List.of("abc123"));
        response.setHeaders(headers);

        FormatOutput output = formatter.format(response);

        assertNotNull(output);
        String formatted = output.toString();

        assertTrue(formatted.contains("[ Fetch User ] ✅ SUCCESS"));
        assertTrue(formatted.contains("Status Code: 200 OK"));
        assertTrue(formatted.contains("Content-Type: [application/json]"));
        assertTrue(formatted.contains("X-Request-Id: [abc123]"));
        assertTrue(formatted.contains("{ \"name\": \"John\" }"));
    }

    @Test
    void testFormat_failureResponse() {
        ClientResponse response = new ClientResponse();
        response.setStatusCode(404);
        response.setStatusMessage("Not Found");
        response.setRequestTitle("Get Order");
        response.setSuccess(false);
        response.setResponseBody("{ \"error\": \"Order not found\" }");

        FormatOutput output = formatter.format(response);

        assertNotNull(output);
        String formatted = output.toString();

        assertTrue(formatted.contains("[ Get Order ] ❌ FAILURE"));
        assertTrue(formatted.contains("Status Code: 404 Not Found"));
        assertTrue(formatted.contains("{ \"error\": \"Order not found\" }"));
    }

    @Test
    void testFormat_nonClientResponseInput_returnsNull() {
        String input = "Not a ClientResponse";
        FormatOutput output = formatter.format(input);
        assertNull(output);
    }

}