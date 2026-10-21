package com.springboot.crime_management_system_backend.mapper;

import com.springboot.crime_management_system_backend.dto.request.OfficerDto;
import com.springboot.crime_management_system_backend.model.Officer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfficerMapper {
    public static Officer convertDtoToEntity(OfficerDto officerDto){

        Officer officer=new Officer();
        officer.setName(officerDto.name());
        officer.setStationId(officerDto.stationId());
        officer.setBadgeNumber(officerDto.badgeNumber());
        return officer;

    }
}
