package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import com.naval_innovators.your_exam_sathi.auth_service.models.enums.Gender;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ExtraDetailsDto {
    private Long universityId;
    private LocalDate dateOfBirth;
    private Gender gender;
    private Long collegeId;
    private Long branchId;
    private int year;

}
