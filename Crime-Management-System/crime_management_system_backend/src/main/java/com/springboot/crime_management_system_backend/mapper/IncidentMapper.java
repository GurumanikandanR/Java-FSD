package com.springboot.crime_management_system_backend.mapper;

import com.springboot.crime_management_system_backend.dto.request.IncidentDto;
import com.springboot.crime_management_system_backend.model.Incident;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IncidentMapper {
    public static Incident covertDtoToEntity(IncidentDto incidentDto){

        Incident incident=new Incident();
        incident.setTypeOfIncident(incidentDto.incidentType());
        incident.setDateOfIncident(incidentDto.date());
        incident.setDescription(incidentDto.description());

        return incident;
    }
}
