package com.springboot.crime_management_system_backend.model;

import com.springboot.crime_management_system_backend.enums.IncidentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "incident")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_incident", nullable = false)
    private IncidentType typeOfIncident;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "date_of_incident", nullable = false)
    private LocalDate dateOfIncident;

    @ManyToOne
    @JoinColumn(name = "civilian_id")
    private Civilian civilian;
}

