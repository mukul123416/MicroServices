package com.lcwd.hotel.service.exceptions;

import com.lcwd.hotel.service.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException ex){
        return ErrorResponse.ResponseHandler(ex.getMessage(),true, HttpStatus.NOT_FOUND);
    }
}