package com.springboot.crime_management_system_backend.dto.response;

import com.springboot.crime_management_system_backend.model.Officer;

public record OfficerDetailsDto(
        Long id,
        String name,
        String badgeNumber,
        String stationId,
        Integer activeCaseCount
) {
}
