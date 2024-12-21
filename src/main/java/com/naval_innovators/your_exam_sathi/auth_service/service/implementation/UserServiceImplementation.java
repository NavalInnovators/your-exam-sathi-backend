package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import java.util.HashMap;
import java.util.Map;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.UserProfile;
import com.naval_innovators.your_exam_sathi.auth_service.models.enums.Gender;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naval_innovators.your_exam_sathi.auth_service.config.JwtUtil;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.SignupRequest;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.models.Role;
import com.naval_innovators.your_exam_sathi.auth_service.models.User;
import com.naval_innovators.your_exam_sathi.auth_service.models.enums.RoleName;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.RolesRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UserRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.OtpService;
import com.naval_innovators.your_exam_sathi.auth_service.service.UserServices;

//import auth_main.config.JwtUtil;
//import auth_main.dtos.LoginRequest;
//import auth_main.dtos.SignupRequest;
//
//import auth_main.models.Profile;
//import auth_main.models.Role;
//import auth_main.models.User;
//import auth_main.models.enums.RoleName;
//import auth_main.repos.ProfileRepository;
//import auth_main.repos.RolesRepository;
//import auth_main.repos.UserRepository;
//import auth_main.services.UserServices;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserServices {

	private final UserRepository userRepository;
	private final ProfileRepository profileRepository;
	private final PasswordEncoder passwordEncoder;
	private final RolesRepository rolesRepository;
	private final JwtUtil jwtUtil;
	private final OtpService otpService;

	@Override
	@Transactional
	public Map<String,String> registerNewUser(SignupRequest signupRequest) {
//		similar email and username filter
		if (userRepository.existsByEmail(signupRequest.getEmail())) {
			throw new IllegalArgumentException("Email already in use.");
		}

		if (userRepository.existsByUserName(signupRequest.getUserName())) {
			throw new IllegalArgumentException("Username already in use.");
		}
		if (userRepository.existsByPhone(signupRequest.getPhone())) {
			throw new IllegalArgumentException("Phone already in use.");
		}
//	    password hashing
		String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
		System.out.println(hashedPassword);
//		Profile Initilization using builder design pattern
		Profile profile = Profile.builder().firstName(signupRequest.getFirstName())
				.lastName(signupRequest.getLastName()).isPremium(false).isVerified(false).build();
//		User Initilization using builder design pattern
		User user = User.builder().userName(signupRequest.getUserName()).password(hashedPassword)
				.email(signupRequest.getEmail()).phone(signupRequest.getPhone()).profile(profile).build();

//		Default Role association using Roles entity
		Role userRole = rolesRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));

		user.setRole(userRole);
		profile.setUser(user);
		userRepository.save(user);
//		need to generate the jwt authentication token
		String accessToken = jwtUtil.createAccessToken(user.getEmail());
		String refreshToken = jwtUtil.createRefreshToken(user.getEmail());
//	    send otp to validate phone number
		otpService.sendOtp(user.getPhone());
//		send email to inform that the account had been created.
		
//		we need to send the otp to the phone'
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("accessToken", accessToken);
		tokens.put("refreshToken", refreshToken);
		return tokens;

		// send otp to the phone and
	}

	@Override
	public UserProfile getUserProfile(Long userId) {
		//Logic for fetching user profile
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));

		return new UserProfile(user);
	}

	@Override
	public void updateUserProfile(Long userId, UserProfile updateProfile) {
		// Validate if the input is empty or null
		if (updateProfile == null || updateProfile.getFirstName() == null || updateProfile.getFirstName().isEmpty()) {
			throw new IllegalArgumentException("First Name is required");
		}
		if (updateProfile.getLastName() == null || updateProfile.getLastName().isEmpty()) {
			throw new IllegalArgumentException("Last Name is required");
		}
		if (updateProfile.getEmail() == null || updateProfile.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email is required");
		}

		// Proceed with regular logic
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found"));

		Profile profile = user.getProfile();
		profile.setFirstName(updateProfile.getFirstName());
		profile.setLastName(updateProfile.getLastName());
		profile.setDateOfBirth(updateProfile.getDateOfBirth());

		// Only update user fields if necessary
		if (updateProfile.getUsername() != null) {
			user.setUserName(updateProfile.getUsername());
		}
		if (updateProfile.getEmail() != null) {
			user.setEmail(updateProfile.getEmail());
		}
		if (updateProfile.getPhone() != null) {
			user.setPhone(updateProfile.getPhone());
		}

		// Handle gender with proper validation
		if (updateProfile.getGender() != null) {
			try {
				Gender genderEnum = Gender.valueOf(updateProfile.getGender().toUpperCase());
				profile.setGender(genderEnum);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Invalid Gender value");
			}
		}

		userRepository.save(user);
	}

}
