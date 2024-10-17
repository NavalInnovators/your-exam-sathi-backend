package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.SignupRequest;

public interface UserServices {
	public String registerNewUser(SignupRequest signUpRequest);
}
