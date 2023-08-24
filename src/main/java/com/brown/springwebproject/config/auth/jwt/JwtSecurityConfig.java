package com.brown.springwebproject.config.auth.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {

//    @Bean
//    public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
//        return http.build();
//    }


}

