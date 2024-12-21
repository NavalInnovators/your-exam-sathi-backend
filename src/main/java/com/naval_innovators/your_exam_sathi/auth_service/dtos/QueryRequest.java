package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class QueryRequest {

    @NotBlank(message = "Query text cannot be empty.")
    private String query;

    @NotNull(message = "Subject must be selected.")
    private Long subjectId;

    @NotNull(message = "User must be specified.")
    private Long userId;

}
