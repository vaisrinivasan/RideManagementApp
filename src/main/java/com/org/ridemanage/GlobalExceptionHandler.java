package com.org.ridemanage;

import com.org.ridemanage.authentication.exception.ErrorResponse;
import com.org.ridemanage.authentication.exception.InvalidInputException;
import com.org.ridemanage.authentication.exception.UserAlreadyExistsException;
import com.org.ridemanage.authentication.exception.UserNotCreatedException;
import com.org.ridemanage.authentication.exception.UserNotRegisteredException;
import com.org.ridemanage.profilemanagement.exception.ProfileAlreadyExistsException;
import com.org.ridemanage.profilemanagement.exception.ProfileNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserNotCreatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserNotCreatedException(UserNotCreatedException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidInputException(InvalidInputException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = UserNotRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserNotRegisteredException(UserNotRegisteredException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = ProfileNotCreatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public com.org.ridemanage.profilemanagement.exception.ErrorResponse handleProfileNotCreatedException(ProfileNotCreatedException exception) {
        return new com.org.ridemanage.profilemanagement.exception.ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(value = ProfileAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public com.org.ridemanage.profilemanagement.exception.ErrorResponse handleProfileAlreadyExistsException(ProfileAlreadyExistsException exception) {
        return new com.org.ridemanage.profilemanagement.exception.ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
