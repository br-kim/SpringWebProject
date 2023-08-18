package com.brown.springwebproject.config.auth.jwt;

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
    private final String secretKey = "2b7820d488114e324c8be406b7818a20a61b666229d0b5ed03c3f2318382140a";

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

        return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(secretKey.getBytes(), algorithm.getName()))
                .macAlgorithm(algorithm)
                .build();
    }

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(secretKey);
    }
}
