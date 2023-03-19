package com.org.ridemanage.profilemanagement.service;

import com.org.ridemanage.authentication.exception.UserAlreadyExistsException;
import com.org.ridemanage.authentication.service.UserServiceImpl;
import com.org.ridemanage.profilemanagement.entity.ProfileEntity;
import com.org.ridemanage.profilemanagement.exception.ProfileAlreadyExistsException;
import com.org.ridemanage.profilemanagement.exception.ProfileNotCreatedException;
import com.org.ridemanage.profilemanagement.exception.ProfileNotFoundException;
import com.org.ridemanage.profilemanagement.model.Profile;
import com.org.ridemanage.profilemanagement.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService{

    private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void createProfile(String userId) {
        try {
            int success = profileRepository.createProfile(UUID.randomUUID().toString(), userId);
            if (success <= 0) {
                logger.error("Error in creating profile");
                throw new ProfileNotCreatedException("Error in creating profile");
            }
        }
        catch(DataIntegrityViolationException exception) {
            logger.error("Profile already created for user");
            throw new ProfileAlreadyExistsException("Profile already created for user");
        }
    }

    @Override
    public void updateProfile(String userId, Profile profile) {
        int success = profileRepository.updateProfile(userId, profile.getFirstName(), profile.getLastName(), profile.getAddress(), new Date(profile.getDateOfBirth().getTime()), profile.getCountry());
        if (success <= 0) {
            logger.error("Error in updating profile");
            throw new ProfileNotCreatedException("Error in updating profile");
        }
    }

    @Override
    public Profile getProfile(String userId) {
        ProfileEntity profileEntity = profileRepository.findByUserId(userId);
        if(profileEntity == null) {
            logger.error("Profile does not exist for the given user id");
            throw new ProfileNotFoundException("Profile does not exist for the given user id");
        }
        Profile profile = Profile.builder().userId(profileEntity.getUserId())
                .firstName(profileEntity.getFirstName())
                .lastName(profileEntity.getLastName())
                .address(profileEntity.getAddress())
                .dateOfBirth(profileEntity.getDateOfBirth())
                .country(profileEntity.getCountry())
                .build();
        return profile;
    }
}
