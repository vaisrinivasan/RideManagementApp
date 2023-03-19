package com.org.ridemanage.profilemanagement.exception;

public class ProfileAlreadyExistsException extends RuntimeException{

    private String message;

    public ProfileAlreadyExistsException() {}

    public ProfileAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
