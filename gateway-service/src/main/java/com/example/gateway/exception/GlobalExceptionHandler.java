package com.example.gateway.exception;

import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Object> resourceAlreadyExist(ResourceAlreadyExistsException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RetryableException.class)
    public ResponseEntity<Object> connectionRefused(RetryableException ex){
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Object> connectionRefused(ConnectException ex){
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NotContentInDatabaseException.class)
    public ResponseEntity<Object> notContentInDatabase(NotContentInDatabaseException nCDEx){
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(nCDEx.getMessage());
    }

}
