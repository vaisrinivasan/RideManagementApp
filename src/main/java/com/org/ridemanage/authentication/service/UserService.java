package com.org.ridemanage.authentication.service;

import com.org.ridemanage.authentication.model.Credential;
import com.org.ridemanage.authentication.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void signupUser(User user);

    String getRegisteredUser(Credential credential);
}
