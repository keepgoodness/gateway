package com.example.db.exception;

public class NotContentInDatabaseException extends RuntimeException {
    public NotContentInDatabaseException(String message) {
        super(message);
    }
}
