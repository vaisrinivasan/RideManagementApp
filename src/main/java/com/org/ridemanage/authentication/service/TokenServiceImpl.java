package com.org.ridemanage.authentication.service;

import com.org.ridemanage.authentication.config.TokenProperties;
import com.org.ridemanage.authentication.exception.JwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    public static final long EXPIRATION_IN_MINUTES = 10l;
    private TokenProperties tokenProperties;

    @Autowired
    public TokenServiceImpl(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    @Override
    public String generateToken(final String id) {
        try {
            final Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(tokenProperties.getTokenSecret()),
                    SignatureAlgorithm.HS256.getJcaName());
            final Instant now = Instant.now();
            final String jwtToken = Jwts.builder()
                    .setId(id)
                    .setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plus(EXPIRATION_IN_MINUTES, ChronoUnit.MINUTES)))
                    .signWith(hmacKey)
                    .compact();
            return jwtToken;
        } catch (JwtException jwtException) {
            logger.error("JWT exception while generating token for id " + id);
            throw new JwtTokenException(jwtException);
        }
    }

    @Override
    public Jws<Claims> parseToken(final String token) {
        try {
            final Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(tokenProperties.getTokenSecret()),
                    SignatureAlgorithm.HS256.getJcaName());
            Jws<Claims> jwt = Jwts.parserBuilder()
                    .setSigningKey(hmacKey)
                    .build()
                    .parseClaimsJws(token);
            return jwt;
        } catch (JwtException jwtException) {
            logger.error("JWT exception while parsing token");
            throw new JwtTokenException(jwtException);
        }
    }
}
