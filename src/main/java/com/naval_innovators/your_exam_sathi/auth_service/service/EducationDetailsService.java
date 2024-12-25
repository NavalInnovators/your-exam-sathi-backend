package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.EducationDetailsDTO;

public interface EducationDetailsService {
    EducationDetailsDTO getEducationDetails(Long profileId);
    boolean setEducationDetails(EducationDetailsDTO dto, Long profileId);
}
