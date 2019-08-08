package com.tuoyou.tavern.auth.libs;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.util.Date;

/**
 * Created by 刘悦之 on 2019/7/7.
 */
public class TokenHelper {

    public static String createToken(JwtAuthenticationProperty config,AuthTokenFactor tokenFactor){
        Instant now = Instant.now();
        String token = Jwts.builder()
                .setSubject(tokenFactor.getUserAccnt())
                .claim(AuthTokenFactor.Factor.USER_ACCNT, tokenFactor.getUserAccnt())
                .claim(AuthTokenFactor.Factor.USER_TYPE, tokenFactor.getUserType())
                .claim(AuthTokenFactor.Factor.ROLE_ID, tokenFactor.getRoleId())
                .setIssuedAt(Date.from(now))
                .setIssuer("TUOYOU")
                .setExpiration(Date.from(now.plusSeconds(config.getExpiration())))
                .signWith(SignatureAlgorithm.HS256, config.getSecret().getBytes())
                .compact();
        return config.getPrefix() + " " + token;
    }
}
