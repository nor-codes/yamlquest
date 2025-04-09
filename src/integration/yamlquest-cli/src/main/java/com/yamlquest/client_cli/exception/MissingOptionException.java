package com.yamlquest.client_cli.exception;

public class MissingOptionException extends RuntimeException{
    public MissingOptionException(String message) {
        super(message);
    }

    public MissingOptionException() {
    }
}
