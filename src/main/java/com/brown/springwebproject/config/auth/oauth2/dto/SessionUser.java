package com.brown.springwebproject.config.auth.oauth2.dto;

import com.brown.springwebproject.user.domain.Users;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private final String email;

    public SessionUser(Users user){
        this.email = user.getEmail();
    }
}
