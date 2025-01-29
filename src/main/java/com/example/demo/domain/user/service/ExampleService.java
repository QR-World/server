package com.example.demo.domain.user.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.global.util.SecurityUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExampleService {
    public void doSomething() {
        UUID userId = SecurityUtil.getCurrentUserId();
        if (userId != null) {
            // User PK 조회
            log.info("User PK: " + userId);
        } else {
            log.warn("No authenticated user");
        }
    }
}