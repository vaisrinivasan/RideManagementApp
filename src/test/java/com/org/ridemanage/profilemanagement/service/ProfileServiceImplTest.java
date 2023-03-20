package com.org.ridemanage.profilemanagement.service;

import com.org.ridemanage.profilemanagement.entity.ProfileEntity;
import com.org.ridemanage.profilemanagement.model.Profile;
import com.org.ridemanage.profilemanagement.repository.ProfileRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ProfileServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @BeforeAll
    public void init() {
        profileService = new ProfileServiceImpl(profileRepository);
    }

    @Test
    public void testGetProfile_WithValidUserId_ShouldReturnProfile() {
        String profileId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        Date date = Date.valueOf(LocalDate.now());
        ProfileEntity profileEntity = new ProfileEntity(profileId, userId, "abc", "def", "sample st", date, "India");
        when(profileRepository.findByUserId(userId)).thenReturn(profileEntity);

        Profile profile = profileService.getProfile(userId);

        assertEquals(userId, profile.getUserId());
        assertEquals("abc", profile.getFirstName());
        assertEquals("def", profile.getLastName());
        assertEquals("sample st", profile.getAddress());
        assertEquals("India", profile.getCountry());
        assertEquals(date, profile.getDateOfBirth());
    }
}
