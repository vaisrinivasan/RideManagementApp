package com.org.ridemanage.service;

import com.org.ridemanage.entity.UserEntity;
import com.org.ridemanage.model.User;
import com.org.ridemanage.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UUID signupUser(User user) {
        UserEntity userEntityRequest = UserEntity.builder()
                .id(UUID.randomUUID())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .createdDate(Date.valueOf(LocalDate.now()))
                .build();
        UserEntity userEntityResponse = userRepository.save(userEntityRequest);
        return userEntityResponse.getId();
    }
}
