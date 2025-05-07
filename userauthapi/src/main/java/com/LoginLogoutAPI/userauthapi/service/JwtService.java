package com.LoginLogoutAPI.userauthapi.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    // Use a secure secret key for production
    private static final String SECRET_KEY = "mysecretkey123456";

    public String generateToken(String email) {
        long now = System.currentTimeMillis();
        long expiry = now + 1000 * 60 * 60; // 1 hour

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(expiry))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
