package com.example.demo.domain.user.controller;

import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.entity.Role;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.global.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public String signUpUser(
            @RequestParam String name,
            @RequestParam String phoneNumber,
            @RequestParam String loginId,
            @RequestParam String password,
            @RequestParam(defaultValue = "NORMAL") String role
    ) {
        User user = User.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .loginId(loginId)
                .password(passwordEncoder.encode(password)) // 비밀번호 암호화
                .role(Role.valueOf(role.toUpperCase()))
                .build();
        userRepository.save(user);
        return "Sign-up successful";
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestParam String loginId,
            @RequestParam String password
    ) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid ID or password");
        }

        String token = jwtTokenProvider.createToken(user.getLoginId());
        return token;
    }
}