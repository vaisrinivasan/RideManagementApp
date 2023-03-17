package com.org.ridemanage.service;

import com.org.ridemanage.exception.UserNotCreatedException;
import com.org.ridemanage.model.User;
import com.org.ridemanage.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void signupUser(User user) {
            int success = userRepository.createUser(UUID.randomUUID(), user.getEmail(), user.getUsername(), user.getPassword(), Date.valueOf(LocalDate.now()));
            if (success <= 0) {
                logger.error("Error in creating user");
                throw new UserNotCreatedException("Error in creating user");
            }
    }
}
