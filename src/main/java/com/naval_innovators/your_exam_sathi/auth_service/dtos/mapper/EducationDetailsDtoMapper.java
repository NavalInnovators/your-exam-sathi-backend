package com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.EducationDetailsDTO;
import com.naval_innovators.your_exam_sathi.auth_service.models.Branch;
import com.naval_innovators.your_exam_sathi.auth_service.models.College;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.models.University;
import com.naval_innovators.your_exam_sathi.auth_service.repository.BranchRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.CollegeRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EducationDetailsDtoMapper {

    private final UniversityRepository universityRepository;
    private final CollegeRepository collegeRepository;
    private final BranchRepository branchRepository;

    public Profile mapToProfile(EducationDetailsDTO educationDetailsDTO, Profile profile) {
        University university = universityRepository.findById(educationDetailsDTO.getUniversityId())
                .orElseThrow(() -> new IllegalArgumentException("University not found"));
        College college = collegeRepository.findById(educationDetailsDTO.getCollegeId())
                .orElseThrow(() -> new IllegalArgumentException("College not found"));
        Branch branch = branchRepository.findById(educationDetailsDTO.getBranchId())
                .orElseThrow(() -> new IllegalArgumentException("Branch not found"));

        profile.setUniversity(university);
        profile.setCollege(college);
        profile.setBranch(branch);
        profile.setYear(educationDetailsDTO.getYear());

        return profile;
    }

}
