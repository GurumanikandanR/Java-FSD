package com.springboot.crime_management_system_backend.service;

import com.springboot.crime_management_system_backend.dto.request.CivilianDto;
import com.springboot.crime_management_system_backend.dto.response.CaseInfoDetailedDto;
import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.enums.Role;
import com.springboot.crime_management_system_backend.exception.ResourceNotFoundException;
import com.springboot.crime_management_system_backend.mapper.CivilianMapper;
import com.springboot.crime_management_system_backend.model.Civilian;
import com.springboot.crime_management_system_backend.model.User;
import com.springboot.crime_management_system_backend.repository.CivilianRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CivilianService {

    private final CivilianRepository civilianRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public void insertCivilian(@Valid CivilianDto civilianDto) {

        //Add user to civilian
        User user=userService.getUserObj(
                civilianDto.username(),
                civilianDto.password(),
                Role.CIVILIAN);

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        Civilian civilian= CivilianMapper.convertDtoToEntity(civilianDto);

        civilian.setUser(user);

        civilianRepository.save(civilian);

    }

    public List<CaseInfoDto> getAllCases(String civilianName, Integer page, Integer size) {
        Pageable pageable =  PageRequest.of(page,size);
        Civilian civilian=civilianRepository.getByName(civilianName);

        if (civilian == null) {
            throw new ResourceNotFoundException("Civilian not found: " + civilianName);
        }

        return civilianRepository.getAllCases(civilian.getId(),pageable);
    }

    public CaseInfoDetailedDto getCaseStatusById(Long caseId) {

        CaseInfoDetailedDto caseInfo =civilianRepository.getCaseStatusById(caseId);

        if (caseInfo == null) {
            throw new ResourceNotFoundException("CaseRecord not found: " + caseId);
        }

        return caseInfo;
    }
}
