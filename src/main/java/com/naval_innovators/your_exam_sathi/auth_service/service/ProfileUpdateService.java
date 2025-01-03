package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileUpdateDTO;

public interface ProfileUpdateService {

    public ProfileUpdateDTO getProfile(Long profileId);

    public Boolean updateProfileDetails(Long profileId, ProfileUpdateDTO profileUpdateDto);

}
