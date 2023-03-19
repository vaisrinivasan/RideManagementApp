package com.org.ridemanage.profilemanagement.service;

import com.org.ridemanage.profilemanagement.model.Profile;

public interface ProfileService {
    void createProfile(String userId);

    void updateProfile(String userId, Profile profile);

    Profile getProfile(String userId);
}
