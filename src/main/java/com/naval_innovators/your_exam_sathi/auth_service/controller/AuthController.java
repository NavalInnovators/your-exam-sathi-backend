package com.naval_innovators.your_exam_sathi.auth_service.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.naval_innovators.your_exam_sathi.auth_service.dtos.OtpRequest;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.OtpResponse;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.SignupRequest;
import com.naval_innovators.your_exam_sathi.auth_service.service.OtpService;
import com.naval_innovators.your_exam_sathi.auth_service.service.UserServices;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
//import auth_main.dtos.LoginRequest;
//import auth_main.dtos.SignupRequest;
//import auth_main.services.UserServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
	@Autowired
	private final UserServices userService;
	@Autowired
	private final OtpService otpService;
	@Autowired
	private final JwtUtil jwtUtil;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signupRequest,
			HttpServletResponse httpResponse) {
		try {
			Map<String, String> tokens = userService.registerNewUser(signupRequest);
			Map<String, String> response = new HashMap<>();
			response.put("message", "User registered successfully.");
			response.put("token", tokens.get("accessToken"));
			httpResponse.addCookie(jwtUtil.setHttpCookie(tokens.get("refreshToken")));
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
	}

	@PostMapping(path = "/otp/validate")
	public ResponseEntity<?> validateOtp(@Validated @RequestBody OtpRequest otpRequest) {
		String message = otpService.validateOtp(otpRequest);
		Map<String, String> response = new HashMap<>();
		response.put("message", message);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

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

//	@PostMapping("/login")
//	public ResponseEntity<?> loginUser(@Validated @RequestBody LoginRequest loginRequest){
////		userService.loginUser(loginRequest);
//		return null;
//	}
}
