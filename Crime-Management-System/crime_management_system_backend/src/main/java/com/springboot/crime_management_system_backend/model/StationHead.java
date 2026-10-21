package com.springboot.crime_management_system_backend.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "station_head")
public class StationHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "badge_number", nullable = false, length = 50)
    private String badgeNumber;

    @Column(name = "station_id", length = 50)
    private String stationId;

    @OneToOne
    private User user;
}
