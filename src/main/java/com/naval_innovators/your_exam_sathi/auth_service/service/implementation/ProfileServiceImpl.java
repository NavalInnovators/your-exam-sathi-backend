package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.CourseResponse;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.ExtraDetailsDto;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileDto;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper.ExtraDetailsDtoMapper;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper.ProfileDtoMapper;
import com.naval_innovators.your_exam_sathi.auth_service.models.Branch;
import com.naval_innovators.your_exam_sathi.auth_service.models.Course;
import com.naval_innovators.your_exam_sathi.auth_service.models.CourseCode;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.models.University;
import com.naval_innovators.your_exam_sathi.auth_service.repository.CourseRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.ProfileService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final CourseRepository courseRepository;
    private final ProfileDtoMapper profileDtoMapper;
    private final ExtraDetailsDtoMapper extraDetailsDtoMapper;

    @Override
    public boolean enrollToCourse(Long profileId, Long courseId) {
        Optional<Profile> profile = profileRepository.findById(profileId);
        Optional<Course> course = courseRepository.findById(courseId);

        if (profile.isPresent() && course.isPresent()) {
            profile.get().getCourses().add(course.get());
            profileRepository.save(profile.get());
            return true;
        }

        return false;
    }

    @Override
    public List<CourseResponse> getCoursesByProfileId(Long profileId) {
        // Step 1: Fetch the profile using the profileId
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + profileId));

        // Step 2: Get the courses associated with the profile (using the relationship
        // table)
        Set<Course> courses = profile.getCourses(); // Assuming 'courses' is a many-to-many relationship in Profile

        // Step 3: Map the courses to CourseResponse DTO
        return courses.stream()
                .map(course -> {
                    // Get the university name(s)
                    String universityName = course.getUniversities().stream()
                            .map(University::getName)
                            .findFirst()
                            .orElse("Unknown");

                    // Get the branch names related to this course
                    List<String> branchNames = course.getBranches().stream()
                            .map(Branch::getName)
                            .collect(Collectors.toList());

                    // Get the course codes
                    List<String> courseCodes = course.getCourseCodes().stream()
                            .map(CourseCode::getCourseCode)
                            .collect(Collectors.toList());

                    return new CourseResponse(
                            course.getId(),
                            course.getName(),
                            course.getYear(),
                            universityName,
                            branchNames,
                            courseCodes);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Boolean setProfileDetails(Long profileId, ProfileDto profileDto) {
        Optional<Profile> profile = profileRepository.findById(profileId);
        if (profile.isPresent()) {
            Profile profileEntity = profile.get();
            profileEntity.setFirstName(profileDto.getFirstName());
            profileEntity.setLastName(profileDto.getLastName());
            profileEntity.setAvatarUrl(profileDto.getAvatarUrl());
            profileEntity.getUser().setUserName(profileDto.getUserName());
            profileEntity.getUser().setEmail(profileDto.getEmail());
            profileEntity.getUser().setPhone(profileDto.getPhone());
            profileEntity.setDateOfBirth(profileDto.getDateOfBirth());
            profileEntity.setGender(profileDto.getGender());
            profileEntity.setYear(profileDto.getYear());
            profileRepository.save(profileEntity);

            return true;
        } else {

            return false;
        }

    }

    @Override
    public ProfileDto getProfile(Long profileId) {

        return profileDtoMapper.mapToDto(profileId);
    }

    @Override
    public boolean setExtraDetails(Long profileId, ExtraDetailsDto extraDetailsDto) {
        return extraDetailsDtoMapper.mapExtraDetailsDto(profileId, extraDetailsDto);
    }
}
