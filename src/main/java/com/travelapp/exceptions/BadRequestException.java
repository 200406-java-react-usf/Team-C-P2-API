package com.travelapp.exceptions;

public class BadRequestException extends TravelappException {

    public BadRequestException() {
        super("An invalid request was made!");
    }

    public BadRequestException(String message) {
        super(message);
    }

}
