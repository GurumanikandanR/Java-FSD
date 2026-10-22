package com.springboot.case_study_evaluation.dto.response;

import com.springboot.case_study_evaluation.enums.Priority;
import com.springboot.case_study_evaluation.enums.Status;

import java.time.LocalDate;

public record TaskRespDto(
        Long id,
        String title,
        String description,
        LocalDate dueDate,
        Priority priority,
        Status status
) {
}
