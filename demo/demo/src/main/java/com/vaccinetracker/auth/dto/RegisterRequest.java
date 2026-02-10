package com.vaccinetracker.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Only Gmail allowed")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^[A-Za-z0-9]{6,20}$",
            message = "Password must be alphanumeric (6-20 chars)"
    )
    private String password;
}
