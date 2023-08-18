package com.brown.springwebproject.config.auth.oauth2;

import com.brown.springwebproject.user.domain.Users;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Map;

@Getter
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String email;
    private final String sub;
    private final String nameAttributeKey;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email, String sub){
        this.attributes = attributes;
        this.email = email;
        this.nameAttributeKey = nameAttributeKey;
        this.sub = sub;
    }

    public static OAuthAttributes of(String registrationId, String userAttributeName, Map<String, Object> attributes) {
        System.out.println(registrationId);
        return ofGoogle(userAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .email((String) attributes.get("email"))
                .sub((String) attributes.get("sub"))
                .attributes(attributes)
                .nameAttributeKey(userAttributeName)
                .build();
    }

    public Users toEntity(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return Users.builder().email(email).googleId(sub).lastLoginAt(timestamp).build();
    }
}
