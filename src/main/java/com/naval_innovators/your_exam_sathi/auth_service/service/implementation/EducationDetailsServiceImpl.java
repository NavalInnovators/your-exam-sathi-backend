package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.EducationDetailsDTO;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper.EducationDetailsDtoMapper;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.repository.BranchRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.CollegeRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UniversityRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.EducationDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationDetailsServiceImpl implements EducationDetailsService {

    private final ProfileRepository profileRepository;
    private final EducationDetailsDtoMapper educationDetailsDtoMapper;
    private final UniversityRepository universityRepository;
    private final CollegeRepository collegeRepository;
    private final BranchRepository branchRepository;

    @Override
    public boolean setEducationDetails(EducationDetailsDTO educationDetailsDTO, Long profileId) {

        if (!universityRepository.existsById(educationDetailsDTO.getUniversityId())) {
            throw new RuntimeException("University not found with ID: " + educationDetailsDTO.getUniversityId());
        }

        // Check if college exists
        if (!collegeRepository.existsById(educationDetailsDTO.getCollegeId())) {
            throw new RuntimeException("College not found with ID: " + educationDetailsDTO.getCollegeId());
        }

        // Check if branch exists
        if (!branchRepository.existsById(educationDetailsDTO.getBranchId())) {
            throw new RuntimeException("Branch not found with ID: " + educationDetailsDTO.getBranchId());
        }

        // Fetch profile by ID
        Optional<Profile> profile = profileRepository.findById(profileId);

        if (profile.isPresent()) {
            try {
                // Map DTO to Profile entity and update profile
                Profile profileEntity = educationDetailsDtoMapper.mapToProfile(educationDetailsDTO, profile.get());
                profileRepository.save(profileEntity);
                return true;
            } catch (Exception e) {
                throw new RuntimeException("Error while setting education details: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Profile not found with ID: " + profileId);
        }
    }

    @Override
    public EducationDetailsDTO getEducationDetails(Long profileId) {
        // Fetch profile by ID
        Optional<Profile> profile = profileRepository.findById(profileId);

        if (profile.isPresent()) {
            try {
                // Map Profile entity to DTO
                EducationDetailsDTO educationDetailsDTO = new EducationDetailsDTO();
                educationDetailsDTO.setUniversityId(profile.get().getUniversity().getId());
                educationDetailsDTO.setCollegeId(profile.get().getCollege().getId());
                educationDetailsDTO.setBranchId(profile.get().getBranch().getId());
                educationDetailsDTO.setYear(profile.get().getYear());

                return educationDetailsDTO;
            } catch (Exception e) {
                throw new RuntimeException("Error while fetching education details: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Profile not found with ID: " + profileId);
        }
    }
}
