package com.springboot.crime_management_system_backend.repository;

import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.model.CaseRecord;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CaseRecordRepository extends JpaRepository<CaseRecord,Long> {
    @Query("""
    SELECT new com.springboot.crime_management_system_backend.dto.response.CaseInfoDto(
            cr.id,i.typeOfIncident,i.description,i.dateOfIncident,cr.caseStatus)
        FROM CaseRecord cr
        JOIN cr.incident i
        WHERE cr.assignedOfficer IS NULL
""")
    List<CaseInfoDto> getUnassignedCaseRecords(Pageable pageable);
}
