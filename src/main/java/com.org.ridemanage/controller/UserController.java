package com.org.ridemanage.controller;

import com.org.ridemanage.model.User;
import com.org.ridemanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<UUID> signupUser(@RequestBody User user) {
        UUID userId = userService.signupUser(user);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }
}
