package com.brown.springwebproject.config.auth.oauth2;

import com.brown.springwebproject.config.auth.jwt.SimpleJwtAuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class CustomOAuth2SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           SimpleJwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/oauth2/**").authenticated()
                .antMatchers("/login/oauth2/**").authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(customOAuth2SuccessHandler)
                .and()
                .authorizeHttpRequests()
                .antMatchers("/auth").authenticated()
                .antMatchers("/**").permitAll()
                .and()
                .oauth2ResourceServer()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);

        return http.build();
    }


}

