package com.revature.revaboards.web.security;

import com.travelapp.web.dtos.Principal;
import com.travelapp.web.security.JwtConfig;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

public class TokenGenerator {

    private TokenGenerator() {
        super();
    }

    public static String createJwt(Principal subject) {

        long now = System.currentTimeMillis();


        JwtBuilder builder = Jwts.builder()
                .setId(Integer.toString(subject.getId()))
                .setSubject(subject.getUsername())
                .claim("role", subject.getRole())
                .setIssuer("revature")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + JwtConfig.EXPIRATION))
                .signWith(JwtConfig.SIG_ALG, JwtConfig.SECRET.getBytes());

        return JwtConfig.PREFIX + builder.compact();

    }
}
