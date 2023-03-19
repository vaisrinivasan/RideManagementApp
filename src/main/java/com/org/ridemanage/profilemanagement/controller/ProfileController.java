package com.org.ridemanage.profilemanagement.controller;

import com.org.ridemanage.authentication.service.TokenService;
import com.org.ridemanage.profilemanagement.model.Profile;
import com.org.ridemanage.profilemanagement.service.ProfileService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    private ProfileService profileService;

    private TokenService tokenService;

    @Autowired
    public ProfileController(ProfileService profileService, TokenService tokenService) {
        this.profileService = profileService;
        this.tokenService = tokenService;
    }

    @PostMapping("/profile/create")
    public ResponseEntity<String> createProfile(@RequestHeader(name = "Authorization") String authorizationHeader) {
        Jws<Claims> jwt = tokenService.parseToken(authorizationHeader);
        profileService.createProfile(jwt.getBody().getId());
        return new ResponseEntity<>("Profile created", HttpStatus.OK);
    }

    @PostMapping("/profile/update")
    public ResponseEntity<String> updateProfile(@RequestHeader(name = "Authorization") String authorizationHeader,
                                                @RequestBody Profile profile) {
        Jws<Claims> jwt = tokenService.parseToken(authorizationHeader);
        profileService.updateProfile(jwt.getBody().getId(), profile);
        return new ResponseEntity<>("Profile updated", HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Profile> getProfile(@RequestHeader(name = "Authorization") String authorizationHeader) {
        Jws<Claims> jwt = tokenService.parseToken(authorizationHeader);
        Profile profile = profileService.getProfile(jwt.getBody().getId());
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}
