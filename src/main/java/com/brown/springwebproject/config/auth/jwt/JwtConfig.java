package com.brown.springwebproject.config.auth.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class JwtConfig {

    @Value("${jwt.secret.key}")
    private String secretKey;

//    @Bean
//    public SecurityFilterChain jwtChain(HttpSecurity http, SimpleJwtAuthenticationConverter jwtAuthenticationConverter) throws Exception{
//        http
//                .oauth2ResourceServer()
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
//                .jwt()
//                .jwtAuthenticationConverter(jwtAuthenticationConverter);
//        return http.build();
//    }


    @Bean
    public JwtDecoder jwtDecoder() {
        MacAlgorithm algorithm = MacAlgorithm.HS256;
        System.out.println("value"+secretKey);
        return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(secretKey.getBytes(), algorithm.getName()))
                .macAlgorithm(algorithm)
                .build();
    }

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(secretKey);
    }
}
