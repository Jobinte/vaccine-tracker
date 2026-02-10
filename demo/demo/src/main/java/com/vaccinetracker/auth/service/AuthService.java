package com.vaccinetracker.auth.service;

import com.vaccinetracker.auth.dto.LoginRequest;
import com.vaccinetracker.auth.dto.RegisterRequest;
import com.vaccinetracker.security.JwtService;
import com.vaccinetracker.auth.entity.User;
import com.vaccinetracker.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // ================= REGISTER =================
    public String register(RegisterRequest request) {

        // check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // create user
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    // ================= LOGIN =================
    public String login(LoginRequest request) {

        // Spring Security checks email + password from DB
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // if authentication success → generate JWT token
        return jwtService.generateToken(request.getEmail());
    }
}
