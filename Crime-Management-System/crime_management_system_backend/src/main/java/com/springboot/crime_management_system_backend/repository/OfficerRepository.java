package com.springboot.crime_management_system_backend.repository;

import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.model.Officer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficerRepository extends JpaRepository<Officer,Long> {
    @Query("""
        select o from Officer o
        Join o.user u
        where u.username=?1
        """)
    Officer getByName(String officerName);

    @Query("""
    SELECT new com.springboot.crime_management_system_backend.dto.response.CaseInfoDto(
            cr.id,i.typeOfIncident,i.description,i.dateOfIncident,cr.caseStatus)
        FROM CaseRecord cr
        JOIN cr.incident i
        WHERE cr.assignedOfficer.id = ?1
""")
    List<CaseInfoDto> viewAssignedCases(Long id, Pageable pageable);
}
