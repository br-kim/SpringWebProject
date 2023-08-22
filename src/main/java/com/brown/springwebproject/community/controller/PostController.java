package com.brown.springwebproject.community.controller;

import com.brown.springwebproject.community.dto.PostCreateRequestDto;
import com.brown.springwebproject.community.dto.PostCreateResponseDto;
import com.brown.springwebproject.community.service.PostService;
import com.brown.springwebproject.config.auth.jwt.UserJwtToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.brown.springwebproject.config.auth.jwt.UserJwtContextHolder.getUserJwtToken;

@RestController
@RequestMapping("/community")
public class PostController {
    private HttpServletRequest request;
    private PostService postService;

    @PostMapping("/write")
    public PostCreateResponseDto writeArticle(@RequestBody PostCreateRequestDto requestDto){
        UserJwtToken userJwtToken = getUserJwtToken();
        postService.createPost(requestDto, userJwtToken.getUserEmail());
        return new PostCreateResponseDto();
    }
}