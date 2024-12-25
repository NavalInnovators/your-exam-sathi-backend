package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QueryRequest {

    @NotBlank(message = "Query text cannot be empty.")
    private String query;

    @NotNull(message = "Course must be selected.")
    private Long courseId;

    @NotNull(message = "Profile must be specified.")
    private Long profileId;

}
