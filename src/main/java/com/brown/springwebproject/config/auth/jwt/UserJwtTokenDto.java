package com.brown.springwebproject.config.auth.jwt;

import java.util.HashMap;
import java.util.Map;

public class UserJwtTokenDto {
    private final Long id;
    private final String email;

    public UserJwtTokenDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Map<String, Object> getClaim(){
        Map<String, Object> claim = new HashMap<>();
        claim.put("id", id);
        claim.put("email", email);
        return claim;
    }
}
