package com.naval_innovators.your_exam_sathi.auth_service.service;

import java.util.Map;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.SignupRequest;

public interface UserServices {
	public Map<String,Object> registerNewUser(SignupRequest signUpRequest);

}
