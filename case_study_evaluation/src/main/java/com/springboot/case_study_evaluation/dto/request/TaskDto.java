package com.springboot.case_study_evaluation.dto.request;

import com.springboot.case_study_evaluation.enums.Priority;
import com.springboot.case_study_evaluation.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TaskDto(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Due date is required")
        LocalDate dueDate,

        @NotNull(message = "Priority is required")
        Priority priority,

        @NotNull(message = "Status is required")
        Status status
) {
}
