package com.springboot.crime_management_system_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record CivilianDto(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Address is required")
        String address,

        @NotNull(message = "Date of birth is required")
        LocalDate dob,

        @NotBlank(message = "Aadhaar number is required")
        @Pattern(regexp = "^[0-9]{12}$",
                message = "Aadhaar number can only contain 12 digits")
        String aadhaarNumber,

        @NotBlank(message = "PAN number is required")
        @Pattern(
                regexp = "[A-Z]{5}[0-9]{4}[A-Z]",
                message = "PAN number must be in a valid format"
        )
        String panNumber,

        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        String password
) {}
