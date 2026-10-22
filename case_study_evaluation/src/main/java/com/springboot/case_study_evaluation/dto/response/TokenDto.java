package com.springboot.case_study_evaluation.dto.response;

import java.util.Date;

public record TokenDto(
        String token,
        Date expiryTime,
        String issueTime
) {
}
