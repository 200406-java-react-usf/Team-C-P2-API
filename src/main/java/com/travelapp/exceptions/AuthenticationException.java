package com.travelapp.exceptions;

public class AuthenticationException extends TravelappException {

    public AuthenticationException() {
        super("Authentication failed!",401);
    }

    public AuthenticationException(String message) {
        super(message,401);
    }

    public AuthenticationException(Throwable cause) {
        super(cause, 401);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause,401);
    }
}
