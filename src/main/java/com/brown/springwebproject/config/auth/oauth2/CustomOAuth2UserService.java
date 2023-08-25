package com.brown.springwebproject.config.auth.oauth2;

import com.brown.springwebproject.config.auth.oauth2.dto.SessionUser;
import com.brown.springwebproject.user.domain.UserRepository;
import com.brown.springwebproject.user.domain.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;


@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;
    private static final Logger logger = Logger.getLogger(CustomOAuth2UserService.class.getName());

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        logger.setLevel(Level.INFO);
        logger.info(userRequest.toString());
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        logger.info(oAuth2User.getAttributes().toString());
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userAttributeName, oAuth2User.getAttributes());
        Users user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
        Collection<? extends GrantedAuthority> authorities =
                Collections.singleton(new OAuth2UserAuthority(oAuth2User.getAttributes()));

        return new DefaultOAuth2User(authorities, attributes.getAttributes(),attributes.getNameAttributeKey());
    }

    private Users saveOrUpdate(OAuthAttributes oAuthAttributes){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Users user = userRepository.findByEmail(oAuthAttributes.getEmail())
                .map(entity -> entity.update(oAuthAttributes.getSub(),timestamp))
                .orElse(oAuthAttributes.toEntity());
        return userRepository.save(user);
    }
}
