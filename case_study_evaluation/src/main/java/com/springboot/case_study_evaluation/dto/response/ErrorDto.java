package com.springboot.case_study_evaluation.dto.response;

import java.time.Instant;

public record ErrorDto(
        String message,
        String comments,
        Instant timestamp
) {
}
