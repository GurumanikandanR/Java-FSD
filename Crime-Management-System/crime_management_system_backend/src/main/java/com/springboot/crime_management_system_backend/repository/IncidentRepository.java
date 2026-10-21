package com.springboot.crime_management_system_backend.repository;

import com.springboot.crime_management_system_backend.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident,Long> {
}
