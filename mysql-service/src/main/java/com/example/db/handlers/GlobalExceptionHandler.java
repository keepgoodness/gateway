package com.example.db.handlers;

import com.example.db.exception.ResourceAlreadyExistsException;
import com.example.db.model.response.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDetails> notCorrectJson(HttpMessageNotReadableException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setInfo("JSON parse error!");
        // logger
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Object> entityExistByRequestId(ResourceAlreadyExistsException ex){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .header("Request-Id", ex.getMessage())
                .body("Request with id " + ex.getMessage() + "' already exists");
    }

}
