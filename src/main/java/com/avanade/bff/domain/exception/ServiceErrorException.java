package com.avanade.bff.domain.exception;

public class ServiceErrorException extends RuntimeException {
    public ServiceErrorException(String message) {
        super(message);
    }
}
