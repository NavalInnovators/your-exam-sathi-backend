package com.naval_innovators.your_exam_sathi.auth_service.service;

import java.util.List;

import com.naval_innovators.your_exam_sathi.auth_service.models.Course;

public interface CoursesService {

    public void addCourse(Course course);

    public List<Course> findAllCourses();
    
}
