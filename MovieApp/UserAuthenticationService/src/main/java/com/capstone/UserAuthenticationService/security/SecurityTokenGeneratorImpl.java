package com.capstone.UserAuthenticationService.security;

import com.capstone.UserAuthenticationService.model.User;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    @Override
    public Map<String, String> generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        System.out.println(claims);
        String email = user.getEmail();

        return createToken(claims, email);
    }

    private Map<String, String> createToken(Map<String, Object> claims, String email) {
        Map<String, String> mapToken = new HashMap<>();
        String token = Jwts.builder().setIssuer("client")
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "mysecretkey").compact();
        mapToken.put("token", token);

        return mapToken;
    }
}
