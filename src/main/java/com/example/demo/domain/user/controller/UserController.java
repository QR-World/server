package com.example.demo.domain.user.controller;

import com.example.demo.domain.user.service.ExampleService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final ExampleService exampleService;

    @GetMapping("/me")
    public String getMyInfo() {
        exampleService.doSomething();
        return "My info";
    }
}