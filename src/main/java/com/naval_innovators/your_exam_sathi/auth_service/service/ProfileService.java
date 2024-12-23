package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileDto;
import com.naval_innovators.your_exam_sathi.auth_service.models.Course;

import java.util.Map;

public interface ProfileService {

    public Boolean setProfileDetails(Long profileId, ProfileDto profileDto);

    public ProfileDto getProfile(Long profileId);

    public boolean enrollToCourse(Long profileId,Long courseId);
}
