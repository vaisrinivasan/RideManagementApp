package com.org.ridemanage.authentication.service;

import com.org.ridemanage.authentication.model.Credential;
import com.org.ridemanage.authentication.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface UserService {

    void signupUser(User user);

    String getRegisteredUser(Credential credential);
}
