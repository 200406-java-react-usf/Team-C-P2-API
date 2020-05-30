package com.travelapp.exceptions;

public class ResourceNotFoundException extends TravelappException {

    public ResourceNotFoundException() {
        super("No resource found with provided search criteria!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
