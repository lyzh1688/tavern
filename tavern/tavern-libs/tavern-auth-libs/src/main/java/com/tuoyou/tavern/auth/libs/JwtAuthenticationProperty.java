package com.tuoyou.tavern.auth.libs;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 刘悦之 on 2019/6/27.
 */
@ConfigurationProperties(prefix = "tavern.security.jwt")
public class JwtAuthenticationProperty {

    private String url = "/login";

    private String header = "Authorization";

    private String prefix = "Bearer";

    private int expiration = 8 * 60 * 60; // default 8 hours

    private String secret;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
