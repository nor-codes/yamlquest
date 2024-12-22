package com.yamlquest.parser.exception;

public class EmptyFilePathException extends Exception{
    public EmptyFilePathException() {
        super();
    }

    public EmptyFilePathException(String message) {
        super(message);
    }

    public EmptyFilePathException(String message, Throwable cause) {
        super(message, cause);
    }
}
