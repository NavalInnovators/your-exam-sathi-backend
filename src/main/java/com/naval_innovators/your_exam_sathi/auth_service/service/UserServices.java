package com.naval_innovators.your_exam_sathi.auth_service.service;

import java.util.Map;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.SignupRequest;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.UserProfile;
import jakarta.servlet.http.Cookie;

public interface UserServices {
	public Map<String,String> registerNewUser(SignupRequest signUpRequest);

	// Method to fetch user profile by ID
	public UserProfile getUserProfile(Long userId);

	// Method to update user profile
	public void updateUserProfile(Long userId, UserProfile updateProfile);
}
