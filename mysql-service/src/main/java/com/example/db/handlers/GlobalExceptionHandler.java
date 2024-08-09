package com.example.db.exception;

import com.example.db.model.response.ErrorDetails;
import com.example.db.model.response.FixerResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ModelMapper modelMapper;

    public GlobalExceptionHandler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder message = new StringBuilder("Validation error: ");
        ex.getBindingResult().getAllErrors().forEach(error ->
                message.append(error.getDefaultMessage()).append("; ")
        );
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setInfo(message.toString());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }
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
