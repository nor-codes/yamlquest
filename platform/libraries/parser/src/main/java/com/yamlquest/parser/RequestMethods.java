package com.yamlquest.parser;

import java.util.HashSet;
import java.util.Set;

public class RequestMethods {
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String GET = "GET";
    public static final String PATCH = "PATCH";
    public static final String DELETE = "DELETE";

    // A Set to store all supported HTTP request methods
    private static final Set<String> supportedMethods = new HashSet<>();

    static {
        supportedMethods.add(POST);
        supportedMethods.add(PUT);
        supportedMethods.add(GET);
        supportedMethods.add(PATCH);
        supportedMethods.add(DELETE);
    }

    // Method to check if a given method is supported
    public static boolean isMethodSupported(String method) {
        return supportedMethods.contains(method);
    }

    // Method to get the set of all supported methods
    public static Set<String> getSupportedMethods() {
        return new HashSet<>(supportedMethods);
    }
}
