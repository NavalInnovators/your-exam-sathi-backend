package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.CourseResponse;
import com.naval_innovators.your_exam_sathi.auth_service.models.Branch;
import com.naval_innovators.your_exam_sathi.auth_service.models.Course;
import com.naval_innovators.your_exam_sathi.auth_service.models.CourseCode;
import com.naval_innovators.your_exam_sathi.auth_service.models.University;
import com.naval_innovators.your_exam_sathi.auth_service.repository.CourseRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.CoursesService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CoursesService {
    private final CourseRepository courseRepository;

    @Override
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> {
                    // Get university name
                    String universityName = course.getUniversities().stream()
                            .map(University::getName)
                            .findFirst()
                            .orElse("Unknown");

                    // Get branch names
                    List<String> branchNames = course.getBranches().stream()
                            .map(Branch::getName)
                            .collect(Collectors.toList());

                    // Get course codes
                    List<String> courseCodes = course.getCourseCodes().stream()
                            .map(CourseCode::getCourseCode)
                            .collect(Collectors.toList());

                    return new CourseResponse(
                            course.getId(),
                            course.getName(),
                            course.getYear(),
                            universityName,
                            branchNames,
                            courseCodes
                    );
                })
                .collect(Collectors.toList());
    }

    
}
