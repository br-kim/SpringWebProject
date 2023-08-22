package com.brown.springwebproject.community.service;

import com.brown.springwebproject.community.domain.Post;
import com.brown.springwebproject.community.domain.PostRepository;
import com.brown.springwebproject.community.dto.PostCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public Post createPost(PostCreateRequestDto requestDto, String author){
        return postRepository.save(requestDto.toEntity(author));
    }

}
