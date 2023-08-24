package com.brown.springwebproject.config.auth.jwt;

import com.brown.springwebproject.user.domain.UserRepository;
import com.brown.springwebproject.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final UserRepository userRepository;

    @Value("${jwt.secret.key}")
    private String secretKey;

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        MacAlgorithm algorithm = MacAlgorithm.HS256;
//        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(new SecretKeySpec(secretKey.getBytes(), algorithm.getName()))
//                .macAlgorithm(algorithm).build();
//        OAuth2TokenValidator<Jwt> withUser = new UserValidator();
//        jwtDecoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(JwtValidators.createDefault()
//                , withUser));
//        return jwtDecoder;
//    }

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(secretKey);

    }

    class UserValidator implements OAuth2TokenValidator<Jwt> {
        OAuth2Error error = new OAuth2Error("custom_code", "Custom error message", null);

        @Override
        public OAuth2TokenValidatorResult validate(Jwt jwt) {
            Long id = jwt.getClaim("id");
            Optional<Users> user = userRepository.findById(id);
            if (user.isPresent()) {
                return OAuth2TokenValidatorResult.success();
            } else {
                return OAuth2TokenValidatorResult.failure(error);
            }
        }
    }

}
