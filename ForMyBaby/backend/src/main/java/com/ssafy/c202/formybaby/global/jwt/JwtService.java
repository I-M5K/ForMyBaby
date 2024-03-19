package com.ssafy.c202.formybaby.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.sql.Date;

@Service
public class JwtService {

    private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // HS512에 적합한 키 생성

    public String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .signWith(key) // 변경된 부분: 생성된 보안 키 사용
                .compact();
    }
}


