package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseResponse {
    private Long id;
    private String name;
    private Integer year;
    private String universityName;
    private List<String> branchNames;
    private List<String> courseCodes;
}
