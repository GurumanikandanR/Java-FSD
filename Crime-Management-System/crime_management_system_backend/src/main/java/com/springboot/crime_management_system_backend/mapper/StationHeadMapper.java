package com.springboot.crime_management_system_backend.mapper;

import com.springboot.crime_management_system_backend.dto.request.StationHeadDto;
import com.springboot.crime_management_system_backend.model.StationHead;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StationHeadMapper {
    public static StationHead convertDtoToEntity(StationHeadDto stationHeadDto){
        StationHead stationHead=new StationHead();
        stationHead.setName(stationHeadDto.name());
        stationHead.setBadgeNumber(stationHeadDto.badgeNumber());
        stationHead.setStationId(stationHeadDto.stationId());
        return stationHead;
    }
}
