package com.naval_innovators.your_exam_sathi.auth_service.service;

import java.util.Map;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.SignupRequest;

import jakarta.servlet.http.Cookie;

public interface UserServices {
	public Map<String,String> registerNewUser(SignupRequest signUpRequest);
}
