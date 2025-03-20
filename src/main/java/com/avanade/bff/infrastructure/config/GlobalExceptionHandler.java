package com.avanade.bff.infrastructure.config;

import com.avanade.bff.domain.exception.CustomerNotFoundException;
import com.avanade.bff.domain.exception.ServiceErrorException;
import com.avanade.bff.domain.exception.ServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exp) {
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(Exception exp) {
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceErrorException.class)
    public ResponseEntity<Object> handleServiceErrorException(Exception exp) {
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Object> handleServiceUnavailableException(Exception exp) {
        return new ResponseEntity<>(exp.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
