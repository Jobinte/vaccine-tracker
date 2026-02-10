package com.vaccinetracker.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    // secret key (later we move to properties file)
    private final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor("my-super-secret-key-my-super-secret-key".getBytes());

    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
                .signWith(SECRET_KEY)
                .compact();
    }
}
