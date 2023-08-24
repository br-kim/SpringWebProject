package com.brown.springwebproject.config.auth.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {

//    private final SimpleJwtAuthenticationConverter jwtAuthenticationConverter;
    private final UserJwtAuthenticationManager userJwtAuthenticationManager;

    @Bean
    public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .antMatcher("/auth")
                .authorizeHttpRequests()
                .anyRequest().authenticated().and()
                .oauth2ResourceServer()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
                .jwt()
                .jwtAuthenticationConverter(new JwtAuthenticationConverter())
                .authenticationManager(userJwtAuthenticationManager);

        return http.build();
    }


}

