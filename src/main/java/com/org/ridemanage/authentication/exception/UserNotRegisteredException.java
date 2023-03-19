package com.org.ridemanage.authentication.exception;

public class UserNotRegisteredException extends RuntimeException{

    private String message;

    public UserNotRegisteredException() {}

    public UserNotRegisteredException(String message) {
        super(message);
        this.message = message;
    }
}