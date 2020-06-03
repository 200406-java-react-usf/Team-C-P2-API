package com.travelapp.exceptions;

public class InternalServerErrorException extends TravelappException {
    public InternalServerErrorException() {
        super("An unexpected error occured. Please try again later.", 500);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause, 500);
    }

    public InternalServerErrorException(String message) {
        super(message, 500);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause, 500);
    }
}
