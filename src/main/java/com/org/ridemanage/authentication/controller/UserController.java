package com.org.ridemanage.authentication.controller;

import com.org.ridemanage.authentication.model.Credential;
import com.org.ridemanage.authentication.model.User;
import com.org.ridemanage.authentication.service.TokenService;
import com.org.ridemanage.authentication.service.UserService;
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
    public ResponseEntity<String> signupUser(@RequestBody User user) {
        userService.signupUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody Credential credential) {
        String userId = userService.getRegisteredUser(credential);
        String token = tokenService.generateToken(userId);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
