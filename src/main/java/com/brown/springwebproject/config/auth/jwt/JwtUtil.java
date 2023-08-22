package com.brown.springwebproject.config.auth.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Bean
    public JwtDecoder jwtDecoder() {
        MacAlgorithm algorithm = MacAlgorithm.HS256;
        return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(secretKey.getBytes(), algorithm.getName()))
                .macAlgorithm(algorithm)
                .build();
    }

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(secretKey);

    }
}
