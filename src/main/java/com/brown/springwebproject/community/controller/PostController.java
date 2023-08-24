package com.brown.springwebproject.community.controller;

import com.brown.springwebproject.community.dto.PostCreateRequestDto;
import com.brown.springwebproject.community.dto.PostCreateResponseDto;
import com.brown.springwebproject.community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community")
public class PostController {
    private final PostService postService;

    @PostMapping("/write")
    public PostCreateResponseDto writeArticle(@RequestBody PostCreateRequestDto requestDto){
        return new PostCreateResponseDto();
    }
}