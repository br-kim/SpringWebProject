package com.brown.springwebproject.config.auth.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserJwtAuthenticationManager implements AuthenticationManager {

    private final UserJwtAuthenticationProvider authenticationProvider;

    public UserJwtAuthenticationManager(UserJwtAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticate = authenticationProvider.authenticate(authentication);
        return authenticate;
    }
}
