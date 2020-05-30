package com.travelapp.exceptions;

public class AuthenticationException extends TravelappException {

    public AuthenticationException() {
        super("Authentication failed!");
    }

    public AuthenticationException(String message) {
        super(message);
    }

}
