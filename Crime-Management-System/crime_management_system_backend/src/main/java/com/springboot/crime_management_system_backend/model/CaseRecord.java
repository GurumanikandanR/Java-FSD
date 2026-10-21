package com.springboot.crime_management_system_backend.model;

import com.springboot.crime_management_system_backend.enums.CaseStatus;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "case_record")
public class CaseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "incident_id", nullable = false)
    private Incident incident;

    @ManyToOne
    @JoinColumn(name = "assigned_officer_id")
    private Officer assignedOfficer;

    @ManyToOne
    @JoinColumn(name = "station_head_id")
    private StationHead stationHead;

    @Enumerated(EnumType.STRING)
    @Column(name = "case_status")
    private CaseStatus caseStatus = CaseStatus.INITIATED;

    @PrePersist
    public void initializeStatus() {
        if (caseStatus == null) {
            caseStatus = CaseStatus.INITIATED;
        }
    }
}

