package com.springboot.crime_management_system_backend.service;

import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.enums.CaseStatus;
import com.springboot.crime_management_system_backend.enums.IncidentType;
import com.springboot.crime_management_system_backend.exception.ResourceNotFoundException;
import com.springboot.crime_management_system_backend.model.CaseRecord;
import com.springboot.crime_management_system_backend.model.Officer;
import com.springboot.crime_management_system_backend.model.StationHead;
import com.springboot.crime_management_system_backend.repository.CaseRecordRepository;
import com.springboot.crime_management_system_backend.repository.OfficerRepository;
import com.springboot.crime_management_system_backend.repository.StationHeadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CaseRecordServiceTest {
    @Mock
    private CaseRecordRepository caseRecordRepository;

    @Mock
    private StationHeadRepository stationHeadRepository;

    @Mock
    private OfficerRepository officerRepository;

    @InjectMocks
    private CaseRecordService caseRecordService;

    @Test
    void unAssignedCaseRecordsTest() {

        List<CaseInfoDto> cases = List.of(
                new CaseInfoDto(
                        1L,
                        IncidentType.LOSTPROPERTY,
                        "A lost mobile phone was reported",
                        LocalDate.of(2026, 10, 15),
                        CaseStatus.INITIATED
                ),
                new CaseInfoDto(
                        2L,
                        IncidentType.GRAFFITI,
                        "Graffiti was reported on a public building",
                        LocalDate.of(2026, 10, 18),
                        CaseStatus.INITIATED
                )
        );
        when(caseRecordRepository.getUnassignedCaseRecords(
                PageRequest.of(0, 5)))
                .thenReturn(cases);

        List<CaseInfoDto> result =
                caseRecordService.unAssignedCaseRecords(0, 5);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).caseId());
        assertEquals(2L, result.get(1).caseId());

        verify(caseRecordRepository).getUnassignedCaseRecords(
                PageRequest.of(0, 5));
    }

    @Test
    void assignOfficerTest() {

        StationHead stationHead = new StationHead();
        Officer officer = new Officer();
        officer.setId(1L);
        officer.setActiveCaseCount(0);

        CaseRecord caseRecord = new CaseRecord();
        caseRecord.setId(1L);
        caseRecord.setCaseStatus(CaseStatus.INITIATED);

        when(stationHeadRepository.getByUsername("stationhead1"))
                .thenReturn(stationHead);

        when(officerRepository.findById(1L))
                .thenReturn(java.util.Optional.of(officer));

        when(caseRecordRepository.findById(1L))
                .thenReturn(java.util.Optional.of(caseRecord));

        caseRecordService.assignOfficer("stationhead1", 1L, 1L);

        assertEquals(officer, caseRecord.getAssignedOfficer());
        assertEquals(stationHead, caseRecord.getStationHead());
        assertEquals(CaseStatus.ACTIVE, caseRecord.getCaseStatus());
        assertEquals(1, officer.getActiveCaseCount());

        verify(officerRepository).save(officer);
        verify(caseRecordRepository).save(caseRecord);

        verifyNoMoreInteractions(stationHeadRepository);
        verifyNoMoreInteractions(officerRepository);
        verifyNoMoreInteractions(caseRecordRepository);
    }

    @Test
    void assignOfficerWithInvalidOfficerIdTest() {

        when(stationHeadRepository.getByUsername("stationhead1"))
                .thenReturn(new StationHead());

        when(officerRepository.findById(50L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> caseRecordService.assignOfficer("stationhead1", 50L, 1L)
        );

        verify(stationHeadRepository).getByUsername("stationhead1");
        verify(officerRepository).findById(50L);

        verifyNoMoreInteractions(stationHeadRepository);
        verifyNoMoreInteractions(officerRepository);
        verifyNoMoreInteractions(caseRecordRepository);
    }
}
