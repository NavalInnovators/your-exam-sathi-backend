package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.models.Course;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.repository.CourseRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final CourseRepository courseRepository;
    @Override
    public boolean enrollToCourse(Long profileId, Long courseId) {
        Optional<Profile> profile = profileRepository.findById(1L);
        Optional<Course> course = courseRepository.findById(courseId);

        if (profile.isPresent() && course.isPresent()) {
            profile.get().getCourses().add(course.get());
            profileRepository.save(profile.get());
            return true;
        }

        return false;

    }
}
