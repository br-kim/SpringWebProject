package com.brown.springwebproject.config.auth.oauth2;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserToken {
    private final String accessToken;
    private final String refreshToken;

    @Builder
    public UserToken(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
