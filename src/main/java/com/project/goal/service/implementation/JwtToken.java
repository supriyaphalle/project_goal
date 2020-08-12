package com.project.goal.service.implementation;

import com.project.goal.exception.JwtTokenException;
import com.project.goal.properties.ApplicationProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

public class JwtToken {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Autowired
    ApplicationProperties applicationProperties;

    Date date = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000);
    int userId;

    public String generateToken(int id) {
        return Jwts.builder().setId(String.valueOf(id)).
                setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, applicationProperties.getSecretKey()).compact();
    }

    public Boolean validateToken(String token) {

        Boolean validate = false;

        if (token.length() <= 2) {

            throw new JwtTokenException("Token must not be empty", JwtTokenException.ExceptionType.EMPTY_TOKEN);
        }

        Claims claims;

        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(applicationProperties.getSecretKey()))
                    .parseClaimsJws(token).getBody();
        } catch (JwtException e) {
            throw new JwtTokenException("Token Expired", JwtTokenException.ExceptionType.TOKEN_EXPIRED);
        }

        userId = Integer.parseInt(claims.getId());
        return true;
    }

    @Override
    public int getUserId() {
        return userId;
    }


}
