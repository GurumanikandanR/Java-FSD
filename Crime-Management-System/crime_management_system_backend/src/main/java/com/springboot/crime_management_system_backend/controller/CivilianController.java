package com.springboot.crime_management_system_backend.controller;

import com.springboot.crime_management_system_backend.dto.request.CivilianDto;
import com.springboot.crime_management_system_backend.dto.response.CaseInfoDetailedDto;
import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.model.Incident;
import com.springboot.crime_management_system_backend.service.CivilianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CivilianController {

    private final CivilianService civilianService;

    @PostMapping("/api/civilian/add")
    public void insertCivilian(@Valid @RequestBody CivilianDto civilianDto){
        civilianService.insertCivilian(civilianDto);
    }

    @GetMapping("/api/civilian/getAllCases")
    public List<CaseInfoDto> getAllCases(Principal principal,
                                         @RequestParam(name = "page" , required = false, defaultValue = "0") Integer page,
                                         @RequestParam(name = "size", required = false, defaultValue = "20") Integer size){
        return civilianService.getAllCases(principal.getName(),page,size);
    }

    @GetMapping("/api/civilian/getCaseStatus/{caseId}")
    public CaseInfoDetailedDto getCaseStatusById(@PathVariable Long caseId){
        return civilianService.getCaseStatusById(caseId);
    }
}
