package com.springboot.crime_management_system_backend.dto.request;

import jakarta.validation.constraints.NotBlank;

public record StationHeadDto(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Badge number is required")
        String badgeNumber,

        @NotBlank(message = "Station ID is required")
        String stationId,

        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        String password
) {
}
