package com.org.ridemanage.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class User {

    private String username;
    private String email;
    private String password;
}
