package com.brown.springwebproject.config.auth.jwt;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserJwtContextHolder {

    public static UserJwtToken getUserJwtToken(){
        return (UserJwtToken) SecurityContextHolder.getContext().getAuthentication();
    }
}
