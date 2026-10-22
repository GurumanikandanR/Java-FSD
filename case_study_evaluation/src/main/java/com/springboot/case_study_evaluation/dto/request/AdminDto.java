package com.springboot.case_study_evaluation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdminDto(

        @NotBlank(message = "Name is required")
        String name,

        @Email(message = "Enter a valid email address")
        String email,

        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        String password
) {
}
