package com.org.ridemanage.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {

    String generateToken(String id);
    Jws<Claims> parseToken(String token);
}
