package com.org.ridemanage.authentication.exception;

public class JwtTokenException extends RuntimeException {

    public JwtTokenException(final Throwable throwable) {
        super(throwable);
    }

    public JwtTokenException(final String msg) {
        super(msg);
    }
}