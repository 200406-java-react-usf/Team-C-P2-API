package com.travelapp.exceptions;

public class ResourcePersistenceException extends TravelappException {

    public ResourcePersistenceException() {
        super("Resource could not be persisted!", 409);
    }

    public ResourcePersistenceException(String message) {
        super(message, 409);
    }

    public ResourcePersistenceException(String message, Throwable cause) {
        super(message, cause, 409);
    }

}
