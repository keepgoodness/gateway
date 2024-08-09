package com.example.gateway.handler;

import com.example.gateway.exception.NotContentInDatabaseException;
import com.example.gateway.exception.ResourceAlreadyExistsException;
import feign.RetryableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Object> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("error", "Unsupported Media Type");
        String contentType = ex.getContentType() == null ? "Missing Content-Type" : "Invalid Content-Type: " + ex.getContentType();
        errorDetails.put("message", contentType + ". Please use 'application/json'.");
        errorDetails.put("path", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(errorDetails);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

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
