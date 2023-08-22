package com.brown.springwebproject.community.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostCreateResponseDto {
    private String status;
    private String errorMessage;
}
