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
    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
//    private final UserJwtAuthenticationConverter jwtAuthenticationConverter;
//    private final UserJwtAuthenticationManager userJwtAuthenticationManager;

    @Bean
    public SecurityFilterChain oAuth2FilterChain(HttpSecurity http) throws Exception {
//        http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
////                .authorizeHttpRequests()
////                .antMatchers("/oauth2/**").authenticated()
////                .antMatchers("/login/oauth2/**").authenticated()
////                .and()
////                .authorizeRequests().antMatchers("/").permitAll()
////                .and()
//                .authorizeRequests().antMatchers("/").permitAll().and()
//                .oauth2Login()
//                .loginPage("/oauth2/authorization/google")
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService)
//                .and()
//                .successHandler(customOAuth2SuccessHandler);
////                .and()
////                .authorizeHttpRequests()
////                .antMatchers("/auth").authenticated()
////                .antMatchers("/**").permitAll()
////                .and()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .oauth2ResourceServer()
////                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
////                .jwt()
////                .jwtAuthenticationConverter(jwtAuthenticationConverter)
////                .authenticationManager(userJwtAuthenticationManager);

        return http.build();
    }


}

