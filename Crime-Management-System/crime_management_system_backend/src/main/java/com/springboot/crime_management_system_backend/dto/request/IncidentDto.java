package com.springboot.crime_management_system_backend.dto.request;

import com.springboot.crime_management_system_backend.enums.IncidentType;

import java.time.LocalDate;

public record IncidentDto(
    IncidentType incidentType,
    String description,
    LocalDate date
) {
}
