package com.brown.springwebproject.index.controller;

import com.brown.springwebproject.index.dto.IndexResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {
    @GetMapping("/")
    public IndexResponseDto index(@RequestParam("name") String name, @RequestParam("email") String email) {
        return new IndexResponseDto(name, email);
    }

    @GetMapping("/auth")
    public String needAuth() {
        return "authed";
    }
}
