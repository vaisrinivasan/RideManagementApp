package com.org.ridemanage.service;

import com.org.ridemanage.exception.InvalidInputException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService{

    @Override
    public String generateToken(final String id) {
        try {
            final Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(tokenSecret),
                    SignatureAlgorithm.HS256.getJcaName());
            final Instant now = Instant.now();
            final String jwtToken = Jwts.builder()
                    .setId(id)
                    .setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plus(10l, ChronoUnit.MINUTES)))
                    .signWith(hmacKey)
                    .compact();
            return jwtToken;
        } catch (JwtException jwtException) {
            throw new InvalidInputException(jwtException);
        }
    }

    @Override
    public Jws<Claims> parseToken(final String token) {
        try {
            final Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(tokenSecret),
                    SignatureAlgorithm.HS256.getJcaName());
            Jws<Claims> jwt = Jwts.parserBuilder()
                    .setSigningKey(hmacKey)
                    .build()
                    .parseClaimsJws(token);
            return jwt;
        } catch (JwtException jwtException) {
            throw new InvalidInputException(jwtException);
        }
    }
}