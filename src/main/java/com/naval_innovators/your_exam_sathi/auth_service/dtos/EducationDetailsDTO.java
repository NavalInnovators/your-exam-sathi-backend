package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDetailsDTO {
    private Long profileId;
    private String universityName;
    private String collegeName;
    private String branchName;
    private int year;
}
