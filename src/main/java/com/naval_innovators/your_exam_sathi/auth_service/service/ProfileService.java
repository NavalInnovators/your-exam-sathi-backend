package com.naval_innovators.your_exam_sathi.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.naval_innovators.your_exam_sathi.auth_service.models.Course;
import java.util.Set;

public interface ProfileService {
    public boolean enrollToCourse(Long profileId,Long courseId);
    
    public Set<Course> getCoursesByUserId(Long userId);
}
