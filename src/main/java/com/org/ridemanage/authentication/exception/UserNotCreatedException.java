package com.org.ridemanage.authentication.exception;

public class UserNotCreatedException extends RuntimeException {

    private String message;

    public UserNotCreatedException() {}

    public UserNotCreatedException(String message) {
        super(message);
        this.message = message;
    }
}
