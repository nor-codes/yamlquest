package com.yamlquest.parser;
import java.util.HashSet;
import java.util.Set;
/**
 * Author: Nonelela Cele
 * Version: 1.0
 * Date: 2024-12-22
 */
public class RequestMethods {
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String GET = "GET";
    public static final String PATCH = "PATCH";
    public static final String DELETE = "DELETE";

    private RequestMethods() {}
    private static final Set<String> supportedMethods = new HashSet<>();

    static {
        supportedMethods.add(POST);
        supportedMethods.add(PUT);
        supportedMethods.add(GET);
        supportedMethods.add(PATCH);
        supportedMethods.add(DELETE);
    }

    public static boolean isMethodSupported(String method) {
        return supportedMethods.contains(method);
    }

    public static Set<String> getSupportedMethods() {
        return new HashSet<>(supportedMethods);
    }
}
