package com.hogar360.users.users.infrastructure.security.utils;

import com.hogar360.users.users.domain.model.UserModel;
import com.hogar360.users.users.infrastructure.security.config.JwtConfig;
import com.hogar360.users.users.infrastructure.security.model.JwtClaimsModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;
    private Key secretKey;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @PostConstruct
    private void init() {
        this.secretKey = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
    }

    public String generateToken(UserModel user) {
        JwtClaimsModel claims = new JwtClaimsModel(user.getEmail(), user.getRole());

        return Jwts.builder()
                .claim(SecurityConstants.EMAIL_CLAIM, claims.getEmail())
                .claim(SecurityConstants.ROLE_CLAIM, claims.getRole())
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return extractClaims(token).get(SecurityConstants.EMAIL_CLAIM, String.class);
    }

    public String extractRole(String token) {
        return extractClaims(token).get(SecurityConstants.ROLE_CLAIM, String.class);
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + jwtConfig.getExpirationMillis());
    }
}
