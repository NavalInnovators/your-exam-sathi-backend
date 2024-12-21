package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.models.Course;

public interface ProfileService {

    public boolean enrollToCourse(Long profileId,Long courseId);
}
