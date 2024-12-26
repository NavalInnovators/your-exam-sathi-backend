package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.CollegeDto;
import com.naval_innovators.your_exam_sathi.auth_service.models.College;

import java.util.List;

public interface CollegeService {

    public List<College> getAllColleges();
    public List<CollegeDto> getCollegeByUniversityId(Long universityId);
}
