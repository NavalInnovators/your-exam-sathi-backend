package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.EducationDetailsDTO;
import com.naval_innovators.your_exam_sathi.auth_service.models.*;
import com.naval_innovators.your_exam_sathi.auth_service.repository.*;
import com.naval_innovators.your_exam_sathi.auth_service.service.EducationDetailsService;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper.EducationDetailsDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EducationDetailsServiceImpl implements EducationDetailsService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private EducationDetailsDtoMapper mapper;

    @Override
    public EducationDetailsDTO getEducationDetails(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> {
                    System.out.println("Profile ID not found: " + profileId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
                });

        if (profile.getUniversity() == null || profile.getCollege() == null || profile.getBranch() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incomplete education details");
        }

        return mapper.profileToEducationDetailsDTO(profile);
    }

    @Override
    public void saveEducationDetails(EducationDetailsDTO dto) {
        // Check if any of the fields are null or empty
        if (dto.getUniversityName() == null || dto.getUniversityName().isEmpty() ||
                dto.getCollegeName() == null || dto.getCollegeName().isEmpty() ||
                dto.getBranchName() == null || dto.getBranchName().isEmpty()) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "University, College, or Branch is missing");
        }

        if (dto.getProfileId() == null || dto.getProfileId().toString().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Profile ID");
        }

        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        // Handle university, college, and branch creation or retrieval
        University university = findOrCreateUniversity(dto.getUniversityName());
        College college = findOrCreateCollege(dto.getCollegeName());

        Branch branch = null;
        if (dto.getBranchName() != null) {
            branch = findOrCreateBranch(dto.getBranchName());
            branch.getColleges().add(college);
            college.getBranches().add(branch);
        }

        profile.setUniversity(university);
        profile.setCollege(college);

        if (branch != null) {
            profile.setBranch(branch);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch cannot be null");
        }

        // Save the profile with updated education details
        profileRepository.save(profile);
    }


    private University findOrCreateUniversity(String universityName) {
        return universityRepository.findByName(universityName)
                .orElseGet(() -> {
                    University newUniversity = new University();
                    newUniversity.setName(universityName);
                    return universityRepository.save(newUniversity);
                });
    }

    private College findOrCreateCollege(String collegeName) {
        return collegeRepository.findByName(collegeName)
                .orElseGet(() -> {
                    College newCollege = new College();
                    newCollege.setName(collegeName);
                    return collegeRepository.save(newCollege);
                });
    }

    private Branch findOrCreateBranch(String branchName) {
        return branchRepository.findByName(branchName)
                .orElseGet(() -> {
                    Branch newBranch = new Branch();
                    newBranch.setName(branchName);
                    return branchRepository.save(newBranch);
                });
    }
}
