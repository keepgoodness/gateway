package com.example.gateway.exception;

public class NotContentInDatabaseException extends RuntimeException {
    public NotContentInDatabaseException(String message) {
        super(message);
    }
}
