package com.springboot.crime_management_system_backend.controller;

import com.springboot.crime_management_system_backend.dto.request.IncidentDto;
import com.springboot.crime_management_system_backend.model.Incident;
import com.springboot.crime_management_system_backend.service.IncidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class IncidentController {
    private final IncidentService incidentService;

    @PostMapping("/api/civilian/post-incident/")
    public void postIncident(Principal principal, @Valid @RequestBody IncidentDto incidentDto){
        incidentService.postIncident(principal.getName(),incidentDto);
    }
}
