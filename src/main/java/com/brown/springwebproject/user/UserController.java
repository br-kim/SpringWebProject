package com.brown.springwebproject.user;

import com.brown.springwebproject.config.auth.oauth2.dto.SessionUser;
import com.brown.springwebproject.user.dto.UserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final HttpSession httpSession;

    @GetMapping("/me")
    public UserInfoResponseDto getLoginUserInfo(){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        String email = "";
        if (user != null){
            email = user.getEmail();
        }
        return new UserInfoResponseDto(email);
    }
}
