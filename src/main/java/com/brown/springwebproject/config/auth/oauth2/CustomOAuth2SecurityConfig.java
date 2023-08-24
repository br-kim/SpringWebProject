package com.brown.springwebproject.config.auth.oauth2;

import com.brown.springwebproject.config.auth.jwt.CustomHeaderFilter;
import com.brown.springwebproject.config.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class CustomOAuth2SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
    private final JwtProvider jwtProvider;
    @Bean
    public SecurityFilterChain oAuth2FilterChain(HttpSecurity http) throws Exception {
        http
                .oauth2Login()
                .loginPage("/oauth2/authorization/google")
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(customOAuth2SuccessHandler).and().addFilterAt(new CustomHeaderFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
