package com.springboot.case_study_evaluation.mapper;

import com.springboot.case_study_evaluation.dto.request.EmployeeDto;
import com.springboot.case_study_evaluation.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public static Employee convertDtoToEntity(EmployeeDto employeeDto){
        Employee employee=new Employee();
        employee.setName(employeeDto.name());
        return employee;
    }
}
