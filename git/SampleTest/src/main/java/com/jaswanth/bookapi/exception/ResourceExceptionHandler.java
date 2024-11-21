package com.jaswanth.bookapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException
            (ResourceNotFoundException ResourceNotFoundException)
    {
        ResourceException ResourceException = new ResourceException(
                ResourceNotFoundException.getMessage(),
                ResourceNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(ResourceException, HttpStatus.NOT_FOUND);
    }
}

