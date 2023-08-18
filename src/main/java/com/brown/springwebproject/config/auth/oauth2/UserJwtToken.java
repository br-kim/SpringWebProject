package com.brown.springwebproject.config.auth.oauth2;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserJwtToken {
    private final String accessToken;
    private final String refreshToken;

    @Builder
    public UserJwtToken(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
