package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDetailsDTO {

    private Long universityId;
    private Long collegeId;
    private Long branchId;

    @Min(1)
    private int year;
}
