package com.vaccinetracker.auth.controller;

import com.vaccinetracker.auth.dto.LoginRequest;
import com.vaccinetracker.auth.dto.RegisterRequest;
import com.vaccinetracker.auth.service .AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    // ⭐ NEW LOGIN API
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
