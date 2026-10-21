package com.springboot.crime_management_system_backend.dto.response;

import com.springboot.crime_management_system_backend.enums.CaseStatus;
import com.springboot.crime_management_system_backend.enums.IncidentType;

import java.time.LocalDate;

public record CaseInfoDto(
        Long caseId,
        IncidentType incidentType,
        String description,
        LocalDate dateOfIncident,
        CaseStatus caseStatus
) {
}
