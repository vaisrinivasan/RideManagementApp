package com.org.ridemanage.controller;

import com.org.ridemanage.model.User;
import com.org.ridemanage.service.TokenService;
import com.org.ridemanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/user/signup")
    public ResponseEntity<UUID> signupUser(@RequestBody User user) {
        userService.signupUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/user/login")
    public ResponseEntity<String> login() {

    }
}
