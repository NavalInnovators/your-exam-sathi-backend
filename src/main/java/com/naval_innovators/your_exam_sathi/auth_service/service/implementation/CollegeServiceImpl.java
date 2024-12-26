package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.CollegeDto;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper.CollegeDtoMapper;
import com.naval_innovators.your_exam_sathi.auth_service.models.College;
import com.naval_innovators.your_exam_sathi.auth_service.repository.CollegeRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UniversityRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.CollegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollegeServiceImpl implements CollegeService {
    private final CollegeRepository collegeRepository;
    private final UniversityRepository universityRepository;
    private final CollegeDtoMapper collegeDtoMapper;

    @Override
    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    @Override
    public List<CollegeDto> getCollegeByUniversityId(Long universityId) {
        List<College> colleges = universityRepository.findById(universityId).get().getColleges();
        List<CollegeDto> collegeDtos = new ArrayList<>();
        for (College college : colleges) {
            collegeDtos.add(collegeDtoMapper.mapToCollegeDto(college));
        }

        return collegeDtos;
    }
}
