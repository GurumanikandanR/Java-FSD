package com.springboot.crime_management_system_backend.controller;

import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.service.CaseRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CaseRecordController {
    private final CaseRecordService caseRecordService;

    @PutMapping("/api/case-record/update/{officerId}/{caseId}")
    public void assignOfficer(Principal principal, @PathVariable Long officerId, @PathVariable Long caseId){
        caseRecordService.assignOfficer(principal.getName(),officerId,caseId);
    }

    @GetMapping("/api/case-record/unassigned")
    public List<CaseInfoDto> unAssignedCaseRecords(@RequestParam(name = "page" , required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(name = "size", required = false, defaultValue = "20") Integer size){
        return caseRecordService.unAssignedCaseRecords(page,size);
    }

    @PutMapping("/api/case-record/updateStatus/{caseId}")
    public void updateCaseStatus(@PathVariable Long caseId){
        caseRecordService.updateCaseStatus(caseId);
    }

    @PutMapping("/api/case-record/verifyStatus/{caseId}")
    public void verifyCaseStatus(@PathVariable Long caseId){
        caseRecordService.verifyCaseStatus(caseId);
    }
}
