package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.CourseResponse;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.ExtraDetailsDto;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileDto;
import org.springframework.beans.factory.annotation.Autowired;

import com.naval_innovators.your_exam_sathi.auth_service.models.Course;

import java.util.List;
import java.util.Set;

public interface ProfileService {
    public boolean enrollToCourse(Long profileId,Long courseId);
    
    public List<CourseResponse> getCoursesByProfileId(Long profileId);

    public Boolean setProfileDetails(Long profileId, ProfileDto profileDto);

    public ProfileDto getProfile(Long profileId);

    public boolean setExtraDetails(Long profileId, ExtraDetailsDto extraDetailsDto);
}
