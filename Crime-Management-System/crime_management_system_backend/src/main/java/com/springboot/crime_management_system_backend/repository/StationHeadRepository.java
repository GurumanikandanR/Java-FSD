package com.springboot.crime_management_system_backend.repository;

import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.dto.response.OfficerDetailsDto;
import com.springboot.crime_management_system_backend.model.StationHead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StationHeadRepository extends JpaRepository<StationHead,Long> {
    @Query("""
            select s from StationHead s
            JOIN s.user u
            where u.username=?1
            """)
    StationHead getByUsername(String stationHeadUsername);

    @Query("""
            SELECT new com.springboot.crime_management_system_backend.dto.response.OfficerDetailsDto(
            o.id,o.name,o.badgeNumber,o.stationId,o.activeCaseCount)
            FROM Officer o
            """)
    List<OfficerDetailsDto> viewAllOfficerDetails(Pageable pageable);

    @Query("""
    SELECT new com.springboot.crime_management_system_backend.dto.response.OfficerDetailsDto(
        o.id,
        o.name,
        o.badgeNumber,
        o.stationId,
        o.activeCaseCount
    )
    FROM Officer o
    WHERE o.id = ?1
    """)
    OfficerDetailsDto viewOfficerDetailsById(Long officerId);

    @Query("""
           SELECT new com.springboot.crime_management_system_backend.dto.response.CaseInfoDto(
           cr.id,i.typeOfIncident,i.description,i.dateOfIncident,cr.caseStatus)
           FROM CaseRecord cr
           JOIN cr.incident i
           WHERE cr.caseStatus = com.springboot.crime_management_system_backend.enums.CaseStatus.CLOSED
           """)
    List<CaseInfoDto> viewClosedCases(Pageable pageable);
}
