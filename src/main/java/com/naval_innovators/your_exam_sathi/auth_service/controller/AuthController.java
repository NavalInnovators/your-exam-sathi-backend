package com.naval_innovators.your_exam_sathi.auth_service.controller;

import java.util.HashMap;
import java.util.Map;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.*;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.responseDTOs.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naval_innovators.your_exam_sathi.auth_service.config.JwtUtil;
import com.naval_innovators.your_exam_sathi.auth_service.service.OtpService;
import com.naval_innovators.your_exam_sathi.auth_service.service.UserServices;
import com.naval_innovators.your_exam_sathi.auth_service.service.implementation.RedisService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
//import auth_main.dtos.LoginRequest;
//import auth_main.dtos.SignupRequest;
//import auth_main.services.UserServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
	@Autowired
	private final UserServices userService;
	@Autowired
	private final OtpService otpService;
	@Autowired
	private final JwtUtil jwtUtil;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> registerUser(@Validated @RequestBody SignupRequest signupRequest,
													 HttpServletResponse httpResponse) {
		try {
			Map<String, Object> tokens = userService.registerNewUser(signupRequest);
			AuthResponse authResponse = new AuthResponse();
			authResponse.setMessage("User registered successfully.");
			authResponse.setAccessToken(tokens.get("accessToken").toString());
			authResponse.setProfileId((Long)tokens.get("profileId"));
			httpResponse.addCookie(jwtUtil.setHttpCookie((String) tokens.get("refreshToken")));
			return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			AuthResponse errorResponse = new AuthResponse();
			errorResponse.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	// @PostMapping(path = "/otp/validate")
	// public ResponseEntity<?> validateOtp(@Validated @RequestBody OtpRequest
	// otpRequest) {
	// String message = otpService.validateOtp(otpRequest);
	// Map<String, String> response = new HashMap<>();
	// response.put("message", message);
	// return new ResponseEntity<>(response, HttpStatus.OK);
	// }

	@PostMapping(path = "/otp/resend")
	public ResponseEntity<?> resendOtp(@RequestBody String phone) {
		otpService.sendOtp(phone);
		Map<String, String> response = new HashMap<>();
		response.put("message", "Otp send successfully.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/test")
	public String testend() {
		return "OK TESTEDcc";
	}

	// login already handled by spring security customauthentication filters.

	// @PostMapping("/login")
	// public ResponseEntity<?> loginUser(@Validated @RequestBody LoginRequest
	// loginRequest){
	//// userService.loginUser(loginRequest);
	// return null;
	// }


}
