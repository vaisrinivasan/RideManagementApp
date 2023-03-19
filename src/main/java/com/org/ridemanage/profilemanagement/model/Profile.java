package com.org.ridemanage.profilemanagement.model;

import lombok.Data;

import java.util.Date;

@Data
public class Profile {
    private String userId;
    private String firstName;
    private String lastName;
    private String address;
    private Date dateOfBirth;
    private String country;
}
