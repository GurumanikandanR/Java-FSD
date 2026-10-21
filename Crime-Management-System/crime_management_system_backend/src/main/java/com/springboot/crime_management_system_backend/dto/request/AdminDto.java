package com.springboot.crime_management_system_backend.dto.request;

public record AdminDto(
        String name,
        String email,
        String username,
        String password
) {
}
