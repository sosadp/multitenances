package com.djsmdev.multitenant.exceptions;

public class TokenException extends RuntimeException {

    private int statusCode;
    public TokenException(String message) {
        super(message);
        this.statusCode = 425;
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = 425;
    }
}
