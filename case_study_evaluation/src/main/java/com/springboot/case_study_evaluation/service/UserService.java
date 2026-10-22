package com.springboot.case_study_evaluation.service;

import com.springboot.case_study_evaluation.dto.request.AdminDto;
import com.springboot.case_study_evaluation.enums.Role;
import com.springboot.case_study_evaluation.model.User;
import com.springboot.case_study_evaluation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User getUserObj(String username, String password, Role role) {
        User user = new User(username, password, role);

        System.out.println("Username: " + user.getUsername());
        System.out.println("Password is null: " + (user.getPassword() == null));

        return userRepository.save(user);
    }

    public void addAdmin(AdminDto adminDto) {
        // Prepare User using AdminDto
        User user = new User(
                adminDto.username(),
                passwordEncoder.encode(adminDto.password()),
                Role.ADMIN
        );

        // Save user in DB
        userRepository.save(user);
    }
}
