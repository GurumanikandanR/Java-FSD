package com.springboot.case_study_evaluation.service;

import com.springboot.case_study_evaluation.dto.request.EmployeeDto;
import com.springboot.case_study_evaluation.enums.Role;
import com.springboot.case_study_evaluation.mapper.EmployeeMapper;
import com.springboot.case_study_evaluation.model.Employee;
import com.springboot.case_study_evaluation.model.User;
import com.springboot.case_study_evaluation.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public void addEmployee(EmployeeDto employeeDto) {

        //Prepare user obj and save in db
        User user=userService.getUserObj(
                employeeDto.username(),
                employeeDto.password(),
                Role.EMPLOYEE
        );

        //Encode Password
        user.setPassword((passwordEncoder.encode(user.getPassword())));

        //Map Dto to entity
        Employee employee= EmployeeMapper.convertDtoToEntity(employeeDto);

        //Attach user obj and save to db
        employee.setUser(user);

        employeeRepository.save(employee);
    }
}
