package com.org.ridemanage.profilemanagement.controller;

import com.org.ridemanage.authentication.model.User;
import com.org.ridemanage.profilemanagement.model.Profile;
import com.org.ridemanage.profilemanagement.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/profile/create")
    public ResponseEntity<UUID> createProfile(@RequestBody Profile profile) {
        profileService.createProfile(profile);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
