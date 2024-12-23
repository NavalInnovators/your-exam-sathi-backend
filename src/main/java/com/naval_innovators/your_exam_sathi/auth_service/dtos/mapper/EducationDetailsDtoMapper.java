package com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper;

import com.naval_innovators.your_exam_sathi.auth_service.models.Branch;
import com.naval_innovators.your_exam_sathi.auth_service.models.College;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.EducationDetailsDTO;
import com.naval_innovators.your_exam_sathi.auth_service.models.University;
import org.springframework.stereotype.Component;

@Component
public class EducationDetailsDtoMapper {

    // Convert Profile entity to EducationDetailsDTO
    public EducationDetailsDTO profileToEducationDetailsDTO(Profile profile) {
        if (profile == null) {
            return null;
        }
        return EducationDetailsDTO.builder()
                .profileId(profile.getId())
                .universityName(profile.getUniversity().getName())
                .collegeName(profile.getCollege().getName())
                .branchName(profile.getBranch().getName())
                .year(profile.getBranch().getYear())
                .build();
    }

    // Convert EducationDetailsDTO to Profile entity
    public Profile educationDetailsDTOToProfile(EducationDetailsDTO dto, Profile profile, University university, College college, Branch branch) {
        if (dto == null) {
            return profile;
        }
        profile.setId(dto.getProfileId());

        // Update university, college, and branch relationships
        profile.setUniversity(university);
        profile.setCollege(college);
        profile.setBranch(branch);
        return profile;
    }
}
