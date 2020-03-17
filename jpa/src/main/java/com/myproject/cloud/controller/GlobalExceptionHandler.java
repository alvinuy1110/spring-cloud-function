package com.myproject.cloud.controller;

import com.myproject.cloud.domain.ErrorDetails;
import com.myproject.cloud.service.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/*
This is a global exception handler when Exceptions are thrown for the @Controller classes

Things to note:

* @ControllerAdvice - tells spring to apply this error handling across the @Controller classes
* The order or the method matters.  The Exception will go through each method, and will only call the first one that
* is applicable
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /*
    @ExceptionHandler - if the specific Exception (or its subclass matches, execute the method)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
