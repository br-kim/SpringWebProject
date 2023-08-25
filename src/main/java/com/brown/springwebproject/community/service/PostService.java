package com.brown.springwebproject.community.service;

import com.brown.springwebproject.community.domain.Post;
import com.brown.springwebproject.community.domain.PostRepository;
import com.brown.springwebproject.community.dto.PostCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public Post createPost(PostCreateRequestDto requestDto, String author){
        return postRepository.save(requestDto.toEntity(author));
    }

    public List<Post> getPosts(int page){
        Pageable pageable = PageRequest.of(page-1,3);
        return postRepository.findAll(pageable).toList();
    }

}

