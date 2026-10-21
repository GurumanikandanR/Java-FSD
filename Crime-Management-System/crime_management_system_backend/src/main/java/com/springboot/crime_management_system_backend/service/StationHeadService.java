package com.springboot.crime_management_system_backend.service;

import com.springboot.crime_management_system_backend.dto.request.StationHeadDto;
import com.springboot.crime_management_system_backend.dto.response.CaseInfoDto;
import com.springboot.crime_management_system_backend.dto.response.OfficerDetailsDto;
import com.springboot.crime_management_system_backend.enums.Role;
import com.springboot.crime_management_system_backend.exception.ResourceNotFoundException;
import com.springboot.crime_management_system_backend.mapper.StationHeadMapper;
import com.springboot.crime_management_system_backend.model.StationHead;
import com.springboot.crime_management_system_backend.model.User;
import com.springboot.crime_management_system_backend.repository.StationHeadRepository;
import com.springboot.crime_management_system_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationHeadService {
    private final StationHeadRepository stationHeadRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public void insertStationHead(StationHeadDto stationHeadDto) {
        User user=userService.getUserObj(stationHeadDto.username(), passwordEncoder.encode(stationHeadDto.password()), Role.STATIONHEAD);

        StationHead stationHead= StationHeadMapper.convertDtoToEntity(stationHeadDto);

        stationHead.setUser(user);

        stationHeadRepository.save(stationHead);
    }

    public List<OfficerDetailsDto> viewAllOfficerDetails(Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page,size);
        return stationHeadRepository.viewAllOfficerDetails(pageable);
    }

    public OfficerDetailsDto viewOfficerDetailsById(Long officerId){
        return stationHeadRepository.viewOfficerDetailsById(officerId);
    }

    public List<CaseInfoDto> viewClosedCases(Integer page, Integer size) {
        Pageable pageable=PageRequest.of(page,size);
        List<CaseInfoDto> closedCases =stationHeadRepository.viewClosedCases(pageable);

        if (closedCases.isEmpty()) {
            throw new ResourceNotFoundException("No closed cases right now.");
        }

        return closedCases;
    }
}
