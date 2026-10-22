package com.springboot.case_study_evaluation.config;

import com.springboot.case_study_evaluation.dto.response.ErrorDto;
import com.springboot.case_study_evaluation.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException e){
        return ResponseEntity
                .badRequest()
                .body(
                        new ErrorDto(e.getMessage(),
                                "Id not Found in DB",
                                Instant.now())
                );
    }
}
