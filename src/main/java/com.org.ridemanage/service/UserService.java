package com.org.ridemanage.service;

import com.org.ridemanage.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    UUID signupUser(User user);
}
