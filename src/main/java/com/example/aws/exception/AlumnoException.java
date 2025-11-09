package com.example.aws.exception;

import org.springframework.http.HttpStatus;

public class AlumnoException extends RuntimeException {
    private final HttpStatus status;

    public AlumnoException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
