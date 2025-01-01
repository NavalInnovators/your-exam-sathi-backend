package com.naval_innovators.your_exam_sathi.auth_service.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.models.User;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UserRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.ProfileService;
import com.naval_innovators.your_exam_sathi.auth_service.service.implementation.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.LoginRequest;
import com.naval_innovators.your_exam_sathi.auth_service.service.implementation.RedisService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	private final JwtUtil jwtUtil;

	private final ProfileRepository profileRepository;
	private final UserRepository userRepository;




	//	custom login end point added.
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, ProfileRepository profileRepository, UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.profileRepository = profileRepository;
		this.userRepository = userRepository;

		this.setFilterProcessesUrl("/api/auth/login");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
//			String requestBody = new BufferedReader(new InputStreamReader(request.);
//			System.out.println("Request Body: " + requestBody);
			// Deserialize the login request

			LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
			// Determine whether to use username or email for authentication
			String identifier = loginRequest.getUserName() != null ? loginRequest.getUserName()
					: loginRequest.getEmail();
//			System.out.println("IDENTIFIER___ "+identifier +"--Password="+loginRequest.getPassword());
			// Create the authentication token
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					identifier, loginRequest.getPassword());
			System.out.println("control here");

			// Authenticate using the token
			return authenticationManager.authenticate(authenticationToken);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
											Authentication authResult) throws IOException {
		SecurityContextHolder.getContext().setAuthentication(authResult);
		String email = authResult.getName();
		String token = jwtUtil.createAccessToken(email);
		User user = userRepository.findByEmail(email);
		if (user == null) {
			user = userRepository.findByUserName(email);
		}
		Long profileId = user.getProfile().getId();

		response.setHeader("Authorization", "Bearer " + token);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{\"validated\": \"" + true + "\",\"token\": \"" + token + "\" , \"profileId\" : " + profileId + "}");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
											  AuthenticationException failed) throws IOException, ServletException {
		if (failed.getMessage().equalsIgnoreCase("Bad credentials")) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.getWriter().write("{\"message\": \"" + "Email or password is incorrect" + "\"}");
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("{\"message\": \"" + "Authentication Failed" + "\"}");
		}
	}

}
