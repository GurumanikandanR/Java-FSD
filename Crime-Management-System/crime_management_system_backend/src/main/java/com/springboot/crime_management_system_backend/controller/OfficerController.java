package com.springboot.crime_management_system_backend.controller;

import com.springboot.crime_management_system_backend.dto.request.OfficerDto;
import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.service.OfficerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OfficerController {
        private final OfficerService officerService;

        @PostMapping("/api/officer/add")
        public void insertOfficer(@Valid @RequestBody OfficerDto officerDto){
            officerService.insertOfficer(officerDto);
        }

        @GetMapping("/api/officer/viewAssignedCases")
        public List<CaseInfoDto> viewAssignedCases(Principal principal,
                                                   @RequestParam(name = "page" , required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(name = "size", required = false, defaultValue = "20") Integer size){
            return officerService.viewAssignedCases(principal.getName(),page,size);
        }
}
