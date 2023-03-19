package com.org.ridemanage.authentication.service;

import com.google.common.base.Strings;
import com.org.ridemanage.authentication.entity.UserEntity;
import com.org.ridemanage.authentication.exception.InvalidInputException;
import com.org.ridemanage.authentication.exception.UserAlreadyExistsException;
import com.org.ridemanage.authentication.exception.UserNotCreatedException;
import com.org.ridemanage.authentication.exception.UserNotRegisteredException;
import com.org.ridemanage.authentication.model.Credential;
import com.org.ridemanage.authentication.model.User;
import com.org.ridemanage.authentication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * A-Z characters are allowed
     * a-z characters are allowed
     * 0-9 numbers are allowed
     * Additionally email can contain dot(.), underscore(_), and dash(-).
     * The remaining characters are not allowed.
     */
    private static final String emailRegexPattern = "^[A-Za-z0-9+_.-]+@(.+)$";

    private static final Pattern pattern = Pattern.compile(emailRegexPattern);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void signupUser(User user) {
        if(Strings.isNullOrEmpty(user.getUsername()) || Strings.isNullOrEmpty(user.getPassword()) || Strings.isNullOrEmpty(user.getEmail())) {
            logger.error("Username, password or email is null or empty");
            throw new InvalidInputException("Username, password or email is null or empty");
        }
        if(!pattern.matcher(user.getEmail()).matches() || user.getPassword().length() < 8) {
            logger.error("Email format is not correct or Password length is less than 8");
            throw new InvalidInputException("Email format is not correct or Password length is less than 8");
        }
        try {
            int success = userRepository.createUser(UUID.randomUUID().toString(), user.getUsername(), user.getEmail(), user.getPassword(), Date.valueOf(LocalDate.now()));
            if (success <= 0) {
                logger.error("Error in creating user");
                throw new UserNotCreatedException("Error in creating user");
            }
        }
        catch(DataIntegrityViolationException exception) {
            logger.error("Username or email already exists");
            throw new UserAlreadyExistsException("Username or email already exists");
        }
    }

    public String getRegisteredUser(Credential credential) {
        if(Strings.isNullOrEmpty(credential.getUsername()) || Strings.isNullOrEmpty(credential.getPassword())) {
            logger.error("Username or password is null or empty");
            throw new InvalidInputException("Username or password is null or empty");
        }
        String userId = userRepository.getUser(credential.getUsername(), credential.getPassword());
        if(userId == null) {
            logger.error("User is not registered with the given username and password");
            throw new UserNotRegisteredException("User is not registered with the given username and password");
        }
        return userId;
    }
}
