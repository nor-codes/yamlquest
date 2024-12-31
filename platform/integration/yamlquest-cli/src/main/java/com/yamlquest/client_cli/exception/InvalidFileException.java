package com.yamlquest.client_cli.exception;

public class InvalidFileException extends RuntimeException{
    public InvalidFileException(String message) {
        super(message);
    }

    public InvalidFileException() {
    }
}
