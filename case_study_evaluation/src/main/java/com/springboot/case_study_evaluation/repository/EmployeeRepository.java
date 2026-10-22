package com.springboot.case_study_evaluation.repository;

import com.springboot.case_study_evaluation.model.Employee;
import com.springboot.case_study_evaluation.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("""
            select e from Employee e
            where  e.user.username = ?1
            """)
    Employee getByUsername(String name);
}
