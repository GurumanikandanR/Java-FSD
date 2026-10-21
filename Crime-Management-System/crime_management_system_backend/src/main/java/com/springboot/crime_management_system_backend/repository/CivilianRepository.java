package com.springboot.crime_management_system_backend.repository;

import com.springboot.crime_management_system_backend.dto.response.CaseInfoDetailedDto;
import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.model.Civilian;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CivilianRepository extends JpaRepository<Civilian,Long> {
    @Query("""
        select new com.springboot.crime_management_system_backend.dto.response.CaseInfoDto(
        cr.id,i.typeOfIncident,i.description,i.dateOfIncident,cr.caseStatus)
        FROM CaseRecord cr
        JOIN cr.incident i
        JOIN i.civilian c
        WHERE c.id = ?1
""")
    List<CaseInfoDto> getAllCases(Long civilianId, Pageable pageable);

    @Query("""
        select c from Civilian c
        Join c.user u
        where u.username=?1
        """)
    Civilian getByName(String civilianName);

    @Query("""
    SELECT new com.springboot.crime_management_system_backend.dto.response.CaseInfoDetailedDto(
            cr.id,i.typeOfIncident,i.description,i.dateOfIncident,cr.caseStatus,o.name)
        FROM CaseRecord cr
        JOIN cr.incident i
        LEFT JOIN cr.assignedOfficer o
        WHERE cr.id = ?1
    """)
    CaseInfoDetailedDto getCaseStatusById(Long caseId);
}
