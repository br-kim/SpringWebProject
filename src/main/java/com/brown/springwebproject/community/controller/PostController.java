package com.brown.springwebproject.community.controller;

import com.brown.springwebproject.community.dto.PostCreateRequestDto;
import com.brown.springwebproject.community.dto.PostCreateResponseDto;
import com.brown.springwebproject.community.service.PostService;
import com.brown.springwebproject.config.auth.oauth2.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community/article")
public class PostController {
    private final PostService postService;
    private final HttpSession httpSession;

    @PostMapping("/write")
    public PostCreateResponseDto writeArticle(@RequestBody PostCreateRequestDto requestDto){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        postService.createPost(requestDto, user.getEmail());
        return new PostCreateResponseDto();
    }
}