package com.brown.springwebproject.config.auth.jwt;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserJwtAuthenticationConverter extends SimpleJwtAuthenticationConverter {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt, Collection<GrantedAuthority> authorities) {
        return new UserJwtToken(jwt, authorities);
    }
}
