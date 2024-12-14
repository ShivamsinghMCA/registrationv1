package com.api.registration.exception;

import com.api.registration.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice  // Controller Advice it is used for handled exception
public class GlobalExceptionHandler {

    // @ExceptionHandler catches the exception and returns a meaningful message
    /* WebRequest request :- this will automatically get the object add in spring boot this can help us to understand from
     where the web request for mad and this exception was thrown which url sends this request to the backend and error is thrown*/
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> resourceNotFound(ResourceNotFoundException r, WebRequest request) {

        ErrorDto error = new ErrorDto(r.getMessage(), new Date(),request.getDescription(true));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
