package com.springboot.case_study_evaluation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EmployeeDto(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        String password
) {
}
