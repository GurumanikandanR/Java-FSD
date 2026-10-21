package com.springboot.crime_management_system_backend.service;

import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.enums.CaseStatus;
import com.springboot.crime_management_system_backend.exception.ResourceNotFoundException;
import com.springboot.crime_management_system_backend.model.CaseRecord;
import com.springboot.crime_management_system_backend.model.Officer;
import com.springboot.crime_management_system_backend.model.StationHead;
import com.springboot.crime_management_system_backend.repository.CaseRecordRepository;
import com.springboot.crime_management_system_backend.repository.OfficerRepository;
import com.springboot.crime_management_system_backend.repository.StationHeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CaseRecordService {
    private final CaseRecordRepository caseRecordRepository;
    private final StationHeadRepository stationHeadRepository;
    private final OfficerRepository officerRepository;

    public void assignOfficer(String staionHeadUsername, Long officerId, Long caseId) {

        StationHead stationHead=stationHeadRepository.getByUsername(staionHeadUsername);

        Officer officer=officerRepository.findById(officerId)
                .orElseThrow(()-> new ResourceNotFoundException("Officer not found"));

        CaseRecord caseRecord=caseRecordRepository.findById(caseId)
                .orElseThrow(()-> new ResourceNotFoundException("Case Record not found"));

        officer.setActiveCaseCount(
                officer.getActiveCaseCount() == null? 1: officer.getActiveCaseCount() + 1);

        caseRecord.setStationHead(stationHead);
        caseRecord.setAssignedOfficer(officer);
        caseRecord.setCaseStatus(CaseStatus.valueOf("ACTIVE"));

        officerRepository.save(officer);
        caseRecordRepository.save(caseRecord);

    }

    public List<CaseInfoDto> unAssignedCaseRecords(Integer page, Integer size) {
        Pageable pageable =  PageRequest.of(page,size);
        return caseRecordRepository.getUnassignedCaseRecords(pageable);
    }

    public void updateCaseStatus(Long caseId) {
        CaseRecord caseRecord = caseRecordRepository.findById(caseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Case not found: " + caseId));

        caseRecord.setCaseStatus(CaseStatus.CLOSED);

        caseRecordRepository.save(caseRecord);
    }

    public void verifyCaseStatus(Long caseId) {
        CaseRecord caseRecord = caseRecordRepository.findById(caseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Case not found: " + caseId));

        if (caseRecord.getCaseStatus() == CaseStatus.VERIFIED) {      //  if its already closed just pass
            return;
        }
        Officer officer = caseRecord.getAssignedOfficer();

        caseRecord.setCaseStatus(CaseStatus.VERIFIED);

        if (officer != null) {
            int activeCount = officer.getActiveCaseCount() == null? 0: officer.getActiveCaseCount();

            if (activeCount > 0) {
                officer.setActiveCaseCount(activeCount - 1);
            }
            officerRepository.save(officer);
        }

        caseRecordRepository.save(caseRecord);
    }
}
