package com.yamlquest.client_cli.utils;
import com.yamlquest.http_client.ClientResponse;
public class RequestWithoutValidationsFormatter<T> extends Formatter<T> {
    @Override
    public FormatOutput format(T input) {
        FormatOutput output = null;
        if (input instanceof ClientResponse response){
            output=getFormatOutput(response);
        }
        return output;
    }

    private FormatOutput getFormatOutput(ClientResponse clientResponse) {
        StringBuilder outputBuilder = new StringBuilder();
        String successIcon = "\u2705"; // ✅
        String failureIcon = "\u274C"; // ❌
        // Request title and success/failure status
        outputBuilder.append("[ ")
                .append(clientResponse.getRequestTitle())
                .append(" ] ")
                .append(clientResponse.isSuccess() ? successIcon + " SUCCESS" : failureIcon + " FAILURE")
                .append("\n");

        // Response details
        outputBuilder.append("Response:\n");
        outputBuilder.append("  - Status Code: ")
                .append(clientResponse.getStatusCode())
                .append(" ")
                .append(clientResponse.getStatusMessage())
                .append("\n");

        // Headers
        if (clientResponse.getHeaders() != null && !clientResponse.getHeaders().isEmpty()) {
            outputBuilder.append("  - Headers:\n");
            clientResponse.getHeaders().forEach((key, value) ->
                    outputBuilder.append("      ").append(key).append(": ").append(value).append("\n"));
        }

        // Body
        outputBuilder.append("  - Response Body:\n")
                .append(clientResponse.getResponseBody())
                .append("\n");

        // Return as FormatOutput
        return new FormatOutput(outputBuilder.toString());
    }

}
