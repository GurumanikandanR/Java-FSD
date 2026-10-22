package com.springboot.case_study_evaluation.controller;

import com.springboot.case_study_evaluation.dto.request.AdminDto;
import com.springboot.case_study_evaluation.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @PostMapping("/api/admin/add")
    public void addAdmin(@Valid @RequestBody AdminDto adminDto) {
        userService.addAdmin(adminDto);
    }
}
