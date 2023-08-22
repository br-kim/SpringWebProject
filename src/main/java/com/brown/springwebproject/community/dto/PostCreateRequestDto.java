package com.brown.springwebproject.community.dto;

import com.brown.springwebproject.community.domain.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostCreateRequestDto {
    private String title;
    private String content;

    public Post toEntity(String author){
        return Post.builder()
                .author(author)
                .title(title)
                .content(content)
                .build();
    }
}
