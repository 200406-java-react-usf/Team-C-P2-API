package com.travelapp.exceptions;

public class ResourceNotFoundException extends TravelappException {

    public ResourceNotFoundException() {
        super("No resource found with provided search criteria!",404);
    }

    public ResourceNotFoundException(String message) {
        super(message,404);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause,404);
    }
}
