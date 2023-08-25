package com.brown.springwebproject.config.auth.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class CustomOAuth2SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain oAuth2FilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/static/**").permitAll()
                .and()
                .oauth2Login()
                .loginPage("/oauth2/authorization/google")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        return http.build();
    }


}
