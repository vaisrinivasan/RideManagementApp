package com.org.ridemanage.profilemanagement.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Profile {
    private String userId;
    private String firstName;
    private String lastName;
    private String address;
    private Date dateOfBirth;
    private String country;
}
