package com.brown.springwebproject.config.auth.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;


public class UserJwtToken extends JwtAuthenticationToken {

    private final Long id;
    private final String email;


    public UserJwtToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
        super(jwt, authorities);
        this.id = Long.valueOf(jwt.getClaimAsString("id"));
        this.email = jwt.getClaimAsString("email");
    }

    public Long getUserId() {
        return id;
    }

    public String getUserEmail() {
        return email;
    }
}