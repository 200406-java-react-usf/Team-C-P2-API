package com.travelapp.exceptions;

public class TravelappException extends RuntimeException {

    public TravelappException(String message) {
        super(message);
    }

    public TravelappException(String message, Throwable cause) {
        super(message, cause);
    }

    public TravelappException(Throwable cause) {
        super(cause);
    }

}
