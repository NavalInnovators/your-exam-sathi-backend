package com.naval_innovators.your_exam_sathi.auth_service.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.SignupRequest;
import com.naval_innovators.your_exam_sathi.auth_service.service.UserServices;

//import auth_main.dtos.LoginRequest;
//import auth_main.dtos.SignupRequest;
//import auth_main.services.UserServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserServices userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signupRequest){
		try {
	        String token = userService.registerNewUser(signupRequest);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("message", "User registered successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    } catch (IllegalArgumentException e) {
	    	Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", e.getMessage());
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);	    }
	}
	
	@GetMapping("/test")
	public String testend() {
		return "OK TESTED";
	}
	
	
	
	
	// login already handled by spring security customauthentication filters.
	
//	@PostMapping("/login")
//	public ResponseEntity<?> loginUser(@Validated @RequestBody LoginRequest loginRequest){
////		userService.loginUser(loginRequest);
//		return null;
//	}
	
	
}
