package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.EducationDetailsDTO;
import com.naval_innovators.your_exam_sathi.auth_service.models.*;
import com.naval_innovators.your_exam_sathi.auth_service.repository.*;
import com.naval_innovators.your_exam_sathi.auth_service.service.EducationDetailsService;
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

    @Override
    public EducationDetailsDTO getEducationDetails(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        if (profile.getUniversity() == null || profile.getCollege() == null || profile.getBranch() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incomplete education details");
        }

        return EducationDetailsDTO.builder()
                .profileId(profile.getId())
                .universityName(profile.getUniversity().getName())
                .collegeName(profile.getCollege().getName())
                .branchName(profile.getBranch().getName())
                .year(profile.getBranch().getYear())
                .build();
    }

    @Override
    public void saveEducationDetails(EducationDetailsDTO dto) {
        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        University university = findOrCreateUniversity(dto.getUniversityName());
        College college = findOrCreateCollege(dto.getCollegeName());
        Branch branch = findOrCreateBranch(dto.getBranchName());

        profile.setUniversity(university);
        profile.setCollege(college);
        profile.setBranch(branch);

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
