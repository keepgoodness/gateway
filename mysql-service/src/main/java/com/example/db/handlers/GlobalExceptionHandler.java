package com.example.db.handlers;

import com.example.db.exception.DataNotFoundException;
import com.example.db.exception.ResourceAlreadyExistsException;
import com.example.db.model.response.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDetails> notCorrectJson(HttpMessageNotReadableException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setInfo("JSON parse error!");
        LOGGER.error("JSON parse error!", ex);
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

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .header("currency", ex.getMessage())
                .body(String.format("Data for currency %s is not found.", ex.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", "Internal server error");
        body.put("details", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
