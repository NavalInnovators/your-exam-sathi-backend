package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.SignupRequest;

public abstract class UserServices {
	public abstract String registerNewUser(SignupRequest signUpRequest);
}
