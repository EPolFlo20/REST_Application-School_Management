package com.example.aws.exception;

import org.springframework.http.HttpStatus;

public class ProfesorException extends RuntimeException {
    private final HttpStatus status;

    public ProfesorException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
