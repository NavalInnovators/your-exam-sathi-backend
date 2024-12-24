package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileDto;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper.ProfileDtoMapper;
import com.naval_innovators.your_exam_sathi.auth_service.models.Course;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.repository.CourseRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.ProfileService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final CourseRepository courseRepository;
    private final ProfileDtoMapper profileDtoMapper;

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
    public Set<Course> getCoursesByUserId(Long userId) {
        return profileRepository.findByUserIdWithCourses(userId)
        .orElseThrow(() -> new RuntimeException("You have not enrolled in any Course!"))
        .getCourses();
    }


    @Override
    public  Boolean setProfileDetails(Long profileId, ProfileDto profileDto) {
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
            profileRepository.save(profileEntity);

            return true;
        }
        else{

            return false;
        }

    }

    @Override
    public ProfileDto getProfile(Long profileId) {

        return profileDtoMapper.mapToDto(profileId);
    }
}
