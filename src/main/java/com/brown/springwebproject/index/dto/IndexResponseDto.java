package com.brown.springwebproject.index.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IndexResponseDto {

    private final String name;
    private final String email;
}
