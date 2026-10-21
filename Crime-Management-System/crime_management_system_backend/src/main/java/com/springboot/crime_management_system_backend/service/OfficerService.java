package com.springboot.crime_management_system_backend.service;

import com.springboot.crime_management_system_backend.dto.request.OfficerDto;
import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.enums.Role;
import com.springboot.crime_management_system_backend.mapper.OfficerMapper;
import com.springboot.crime_management_system_backend.model.Officer;
import com.springboot.crime_management_system_backend.model.User;
import com.springboot.crime_management_system_backend.repository.OfficerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficerService {
    private final OfficerRepository officerRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public void insertOfficer(@Valid OfficerDto officerDto) {

        User user=userService.getUserObj(officerDto.username(), passwordEncoder.encode(officerDto.password()), Role.OFFICER);

        Officer officer= OfficerMapper.convertDtoToEntity(officerDto);
        officer.setUser(user);

        officerRepository.save(officer);

    }

    public List<CaseInfoDto> viewAssignedCases(String name, Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page,size);
        Officer officer=officerRepository.getByName(name);
        return officerRepository.viewAssignedCases(officer.getId(),pageable);
    }


}
