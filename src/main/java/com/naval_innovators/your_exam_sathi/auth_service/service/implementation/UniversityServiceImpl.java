package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.UniversityDto;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper.UniversityDtoMapper;
import com.naval_innovators.your_exam_sathi.auth_service.models.University;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UniversityRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;
    private final UniversityDtoMapper universityDtoMapper;
    @Override
    public List<UniversityDto> getAllUniversities() {
        List<University> universities = universityRepository.findAll();
        List<UniversityDto> universityDtos = new ArrayList<>();
        for (University university : universities) {
           universityDtos.add(universityDtoMapper.map(university));
        }
        return universityDtos;
    }
}
