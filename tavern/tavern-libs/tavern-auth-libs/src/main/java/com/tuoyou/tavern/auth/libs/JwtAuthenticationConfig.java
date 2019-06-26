package com.tuoyou.tavern.auth.libs;

import org.springframework.beans.factory.annotation.Value;

public class JwtAuthenticationConfig {

    @Value("${tavern.security.jwt.url:/login}")
    private String url;

    @Value("${tavern.security.jwt.header:Authorization}")
    private String header;

    @Value("${tavern.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${tavern.security.jwt.expiration:#{8*60*60}}")
    private int expiration; // default 8 hours

    @Value("${tavern.security.jwt.secret}")
    private String secret;

    public String getUrl() {
        return url;
    }

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }
}
