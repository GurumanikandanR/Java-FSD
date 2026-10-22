package com.springboot.case_study_evaluation.controller;

import com.springboot.case_study_evaluation.dto.request.EmployeeDto;
import com.springboot.case_study_evaluation.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/api/employee/add")
    public void addEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        employeeService.addEmployee(employeeDto);
    }
}
