package com.travelapp.exceptions;

public class BadRequestException extends TravelappException {

    public BadRequestException() {
        super("An invalid request was made!", 400);
    }

    public BadRequestException(String message) {
        super(message,400);
    }

    public BadRequestException(Throwable cause) {
        super(cause, 400);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause,400);
    }
}
