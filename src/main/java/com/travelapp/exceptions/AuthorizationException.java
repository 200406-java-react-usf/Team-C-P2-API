package com.travelapp.exceptions;

public class AuthorizationException extends TravelappException {

    public AuthorizationException(String message) {
        super(message,403);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause,403);
    }

    public AuthorizationException(Throwable cause) {
        super(cause, 403);
    }

    public AuthorizationException() {
        super("You are not authorized to enter",403);
    }
}
