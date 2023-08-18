package com.brown.springwebproject.config.auth.oauth2;

import com.brown.springwebproject.config.auth.jwt.JwtProvider;
import com.brown.springwebproject.user.domain.UserRepository;
import com.brown.springwebproject.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        UserJwtToken userJwtToken = null;
        try {
            userJwtToken = createUserJwtToken(authentication);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String targetUrl = determineTargetUrl(request, response, authentication);
        getRedirectStrategy().sendRedirect(request,response,getRedirectUrl(targetUrl, userJwtToken));
    }

    private UserJwtToken createUserJwtToken (Authentication authentication) throws Exception {
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attribute = oAuth2User.getAttributes();
        Users user = userRepository.findByEmail((String) attribute.get("email")).orElseThrow();
        Map<String, Object> tokenData = Map.of("email",user.getEmail(), "id",user.getId());
        String accessToken = jwtProvider.generateAccessToken(tokenData);
        String refreshToken = jwtProvider.generateRefreshToken(tokenData);
        return new UserJwtToken(accessToken, refreshToken);
    }

    private String getRedirectUrl(String targetUrl, UserJwtToken token){
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("accessToken",token.getAccessToken())
                .queryParam("refreshToken",token.getRefreshToken())
                .build().toUriString();
    }
}
