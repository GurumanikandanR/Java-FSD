package com.springboot.crime_management_system_backend.controller;

import com.springboot.crime_management_system_backend.dto.request.AdminDto;
import com.springboot.crime_management_system_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @PostMapping("/api/admin/add")
    public void addAdmin(@RequestBody AdminDto adminDto) {
        userService.addAdmin(adminDto);
    }
}
