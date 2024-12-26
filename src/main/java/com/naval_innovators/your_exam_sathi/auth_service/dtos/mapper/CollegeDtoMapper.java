package com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.CollegeDto;
import com.naval_innovators.your_exam_sathi.auth_service.models.College;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollegeDtoMapper {

    public CollegeDto mapToCollegeDto(College college){
        CollegeDto collegeDto = new CollegeDto();
        collegeDto.setId(college.getId());
        collegeDto.setName(college.getName());
        return collegeDto;
    }
}
