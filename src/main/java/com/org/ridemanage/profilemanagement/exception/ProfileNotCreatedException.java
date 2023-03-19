package com.org.ridemanage.profilemanagement.exception;

public class ProfileNotCreatedException extends RuntimeException {

    private String message;

    public ProfileNotCreatedException() {}

    public ProfileNotCreatedException(String message) {
        super(message);
        this.message = message;
    }
}