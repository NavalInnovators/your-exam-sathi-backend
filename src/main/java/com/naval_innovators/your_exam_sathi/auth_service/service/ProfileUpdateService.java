package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileUpdateDto;


public interface ProfileUpdateService {

    public ProfileUpdateDto getUserProfile(Long userId);

    public void updateUserProfile(Long userId, ProfileUpdateDto updateProfile);

}
