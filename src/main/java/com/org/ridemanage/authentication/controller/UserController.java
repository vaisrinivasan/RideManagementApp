package com.org.ridemanage.authentication.controller;

import com.org.ridemanage.authentication.model.Credential;
import com.org.ridemanage.authentication.model.User;
import com.org.ridemanage.authentication.service.TokenService;
import com.org.ridemanage.authentication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> signupUser(@RequestBody User user) {
        userService.signupUser(user);
        logger.info("User signup successful for " + user.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody Credential credential) {
        String userId = userService.getRegisteredUser(credential);
        String token = tokenService.generateToken(userId);
        logger.info("User login successful for " + credential.getUsername());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
