package com.springboot.crime_management_system_backend.controller;

import com.springboot.crime_management_system_backend.dto.request.StationHeadDto;
import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.dto.response.OfficerDetailsDto;
import com.springboot.crime_management_system_backend.service.StationHeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StationHeadController {
    private final StationHeadService stationHeadService;

    @PostMapping("/api/stationHead/add")
    public void insertStationHead(@RequestBody StationHeadDto stationHeadDto){
        stationHeadService.insertStationHead(stationHeadDto);
    }

    @GetMapping("/api/stationHead/viewAllOfficer")
    public List<OfficerDetailsDto> viewAllOfficerDetails(@RequestParam(name = "page" , required = false, defaultValue = "0") Integer page,
                                                         @RequestParam(name = "size", required = false, defaultValue = "20") Integer size){
        return stationHeadService.viewAllOfficerDetails(page,size);
    }

    @GetMapping("/api/stationHead/viewDetails/{officerId}")
    public OfficerDetailsDto viewOfficerDetailsById(@PathVariable Long officerId){
        return stationHeadService.viewOfficerDetailsById(officerId);
    }

    @GetMapping("/api/stationHead/viewCaseStatus")
    public List<CaseInfoDto> viewClosedCases(@RequestParam(name = "page" , required = false, defaultValue = "0") Integer page,
                                             @RequestParam(name = "size", required = false, defaultValue = "20") Integer size){
        return stationHeadService.viewClosedCases(page,size);
    }
}
