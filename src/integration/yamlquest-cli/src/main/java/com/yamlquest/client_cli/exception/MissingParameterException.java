package com.yamlquest.client_cli.exception;

public class MissingParameterException extends RuntimeException{
    public MissingParameterException(String message) {
        super(message);
    }

    public MissingParameterException() {
    }
}
