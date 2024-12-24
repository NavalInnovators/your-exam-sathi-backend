package com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.ExtraDetailsDto;
import com.naval_innovators.your_exam_sathi.auth_service.models.Branch;
import com.naval_innovators.your_exam_sathi.auth_service.models.College;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.models.University;
import com.naval_innovators.your_exam_sathi.auth_service.repository.BranchRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.CollegeRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UniversityRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExtraDetailsDtoMapper {
    private final ProfileRepository profileRepository;
    private final CollegeRepository collegeRepository;
    private final UniversityRepository universityRepository;
    private final BranchRepository branchRepository;


    public boolean mapExtraDetailsDto(Long profileId,ExtraDetailsDto extraDetailsDto) {
        Profile profile = profileRepository.findById(profileId).orElse(null);
        if (profile == null) {
            return false;
        }
        College college = collegeRepository.findById(extraDetailsDto.getCollegeId()).orElse(null);
        if (college == null) {
            throw new RuntimeException("College not found");
        }

        University university = universityRepository.findById(extraDetailsDto.getUniversityId()).orElse(null);
        if (university == null) {
            throw new RuntimeException("University not found");
        }

        Branch branch = branchRepository.findById(extraDetailsDto.getBranchId()).orElse(null);
        if (branch == null) {
            throw new RuntimeException("Branch not found");
        }

        profile.setCollege(college);
        profile.setUniversity(university);
        profile.setYear(extraDetailsDto.getYear());
        profile.setBranch(branch);
        profile.setGender(extraDetailsDto.getGender());
        profile.setDateOfBirth(extraDetailsDto.getDateOfBirth());

        profileRepository.save(profile);
        return true;

    }
}
