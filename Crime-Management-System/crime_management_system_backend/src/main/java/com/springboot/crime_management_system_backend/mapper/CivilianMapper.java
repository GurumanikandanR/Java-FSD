package com.springboot.crime_management_system_backend.mapper;

import com.springboot.crime_management_system_backend.dto.request.CivilianDto;
import com.springboot.crime_management_system_backend.model.Civilian;
import org.springframework.stereotype.Component;

@Component
public class CivilianMapper {
    public static Civilian convertDtoToEntity(CivilianDto civilianDto){
        Civilian civilian=new Civilian();
        civilian.setAadhaarNumber(civilianDto.aadhaarNumber());
        civilian.setAddress(civilianDto.address());
        civilian.setDob(civilianDto.dob());
        civilian.setName(civilianDto.name());
        civilian.setPanNumber(civilianDto.panNumber());
        return civilian;
    }
}
