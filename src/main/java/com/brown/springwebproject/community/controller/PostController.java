package com.brown.springwebproject.community.controller;

import com.brown.springwebproject.community.domain.Post;
import com.brown.springwebproject.community.dto.PostCreateRequestDto;
import com.brown.springwebproject.community.dto.PostGetResponseDto;
import com.brown.springwebproject.community.service.PostService;
import com.brown.springwebproject.config.auth.oauth2.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community/article")
public class PostController {
    private final PostService postService;
    private final HttpSession httpSession;

    @PostMapping("/write")
    public Long writeArticle(@RequestBody PostCreateRequestDto requestDto){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        Post post = postService.createPost(requestDto, user.getEmail());
        return post.getId();
    }

    @GetMapping("")
    public PostGetResponseDto getArticles(@RequestParam int page){
        return new PostGetResponseDto(postService.getPosts(page));
    }
}