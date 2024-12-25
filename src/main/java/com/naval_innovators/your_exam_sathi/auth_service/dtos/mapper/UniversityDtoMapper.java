package com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.UniversityDto;
import com.naval_innovators.your_exam_sathi.auth_service.models.University;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniversityDtoMapper {

    public UniversityDto map(University university) {
        UniversityDto universityDto = new UniversityDto();
        universityDto.setUniversityId(university.getId());
        universityDto.setUniversityName(university.getName());
        return universityDto;
    }
}
