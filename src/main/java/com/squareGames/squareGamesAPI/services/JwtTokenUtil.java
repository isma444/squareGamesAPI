package com.squareGames.squareGamesAPI.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtil {


    private final long TOKEN_EXPIRATION = 3600 * 1000L;

    @Value("${jwt.secret}")
    private String key;


    public String generateJwtToken() {

        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());

        return Jwts.builder()
                .setSubject(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .signWith(secretKey)
                .compact();
    }

}
