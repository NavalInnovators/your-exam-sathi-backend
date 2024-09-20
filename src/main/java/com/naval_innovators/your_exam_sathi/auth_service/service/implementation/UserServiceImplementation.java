package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.naval_innovators.your_exam_sathi.auth_service.config.JwtUtil;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.SignupRequest;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.models.Role;
import com.naval_innovators.your_exam_sathi.auth_service.models.User;
import com.naval_innovators.your_exam_sathi.auth_service.models.enums.RoleName;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.RolesRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UserRepository;
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
public class UserServiceImplementation extends UserServices{

	private final UserRepository userRepository;
	private final ProfileRepository profileRepository;
	private final PasswordEncoder passwordEncoder;
	private final RolesRepository rolesRepository;
	private final JwtUtil jwtUtil;
	
	@Override
	public String registerNewUser(SignupRequest signupRequest) {
//		similar email and username filter
		if (userRepository.existsByEmail(signupRequest.getEmail())) {
	        throw new IllegalArgumentException("Email already in use.");
	    }

	    if (userRepository.existsByUserName(signupRequest.getUserName())) {
	        throw new IllegalArgumentException("Username already in use.");
	    }
	    
//	    password hashing
		String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
		
//		Profile Initilization using builder design pattern
		Profile profile = Profile.builder()
				.firstName(signupRequest.getFirstName())
				.lastName(signupRequest.getLastName())
				.phoneNumber(signupRequest.getPhone())
				.isPremium(false)
				.isVerified(false).build();
//		User Initilization using builder design pattern
		User user = User.builder()
				.userName(signupRequest.getUserName())
				.password(hashedPassword)
				.email(signupRequest.getEmail())
				.profile(profile).build();
//		Default Role association using Roles entity
		Role userRole = rolesRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		
		user.setRole(userRole);
		profile.setUser(user);
		userRepository.save(user);
		
		String token = jwtUtil.generateToken(user.getUserName());
		
		return token;
	}

}
