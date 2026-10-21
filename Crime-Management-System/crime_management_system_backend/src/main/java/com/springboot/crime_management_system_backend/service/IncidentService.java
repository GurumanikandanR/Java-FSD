package com.springboot.crime_management_system_backend.service;

import com.springboot.crime_management_system_backend.dto.request.IncidentDto;
import com.springboot.crime_management_system_backend.exception.ResourceNotFoundException;
import com.springboot.crime_management_system_backend.mapper.IncidentMapper;
import com.springboot.crime_management_system_backend.model.CaseRecord;
import com.springboot.crime_management_system_backend.model.Civilian;
import com.springboot.crime_management_system_backend.model.Incident;
import com.springboot.crime_management_system_backend.repository.CaseRecordRepository;
import com.springboot.crime_management_system_backend.repository.CivilianRepository;
import com.springboot.crime_management_system_backend.repository.IncidentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final CivilianRepository civilianRepository;
    private final CaseRecordRepository caseRecordRepository;

    public void postIncident(String civilianName,@Valid IncidentDto incidentDto) {
        Civilian civilian=civilianRepository.getByName(civilianName);

        if (civilian == null) {
            throw new ResourceNotFoundException("Civilian not found: " + civilianName);
        }

        Incident incident= IncidentMapper.covertDtoToEntity(incidentDto);

        incident.setCivilian(civilian);

        incidentRepository.save(incident);

        CaseRecord caseRecord=new CaseRecord();
        caseRecord.setIncident(incident);
        caseRecordRepository.save(caseRecord);
    }
}
