package com.org.ridemanage.exception;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(final Throwable throwable) {
        super(throwable);
    }

    public InvalidInputException(final String msg) {
        super(msg);
    }
}