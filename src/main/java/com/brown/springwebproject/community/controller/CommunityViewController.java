package com.brown.springwebproject.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/community")
public class CommunityViewController {
    private HttpServletRequest request;

    @GetMapping("/page")
    public String index() {
        return "community";
    }

    @GetMapping("/write/page")
    public String writeTemplate() {
        return "writePage";
    }
}