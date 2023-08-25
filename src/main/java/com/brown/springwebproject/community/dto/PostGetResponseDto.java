package com.brown.springwebproject.community.dto;

import com.brown.springwebproject.community.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PostGetResponseDto {
    private final List<Post> posts;

}
