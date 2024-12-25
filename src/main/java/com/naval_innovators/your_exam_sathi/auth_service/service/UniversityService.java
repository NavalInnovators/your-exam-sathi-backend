package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.UniversityDto;
import com.naval_innovators.your_exam_sathi.auth_service.models.University;

import java.util.List;

public interface UniversityService {

    List<UniversityDto> getAllUniversities();
}
