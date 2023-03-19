package com.org.ridemanage.profilemanagement.exception;

public class ProfileNotFoundException extends RuntimeException {

    private String message;

    public ProfileNotFoundException() {}

    public ProfileNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}