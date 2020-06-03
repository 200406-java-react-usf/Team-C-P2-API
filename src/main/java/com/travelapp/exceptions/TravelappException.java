package com.travelapp.exceptions;

public class TravelappException extends RuntimeException {

    public int status;

    public TravelappException(int status) {
        this.status = status;
    }

    public TravelappException(Throwable cause, int status) {
        super(cause);
        this.status = status;
    }

    public TravelappException(String message, int status) {
        super(message);
        this.status = status;
    }

    public TravelappException(String message, Throwable cause, int status) {
        super(message, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
