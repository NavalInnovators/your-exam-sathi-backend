package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryResponse {
    private String query;
    private String answer;
    private LocalDate submissionDate;
}
