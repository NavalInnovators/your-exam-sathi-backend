package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.naval_innovators.your_exam_sathi.auth_service.config.JwtUtil;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.OtpRequest;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.OtpResponse;
import com.naval_innovators.your_exam_sathi.auth_service.service.OtpService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
	private final RedisService otpRedisService;
	private final JwtUtil jwtUtil;

	@Override
	public void sendOtp(String phone) {
		String otp = generateOtp();
		System.out.println(otp);
		otpRedisService.saveOtp(phone, otp);
//		send otp using twillio
	}

	// @Override
	// public String validateOtp(OtpRequest otpRequest) {
	// 	String message;
	// 	if (otpRedisService.validateOtp(otpRequest)) {
	// 		message = "Account Verified";
	// 	} else {
	// 		message = "Otp validation failed";
	// 	}
	// 	return message;
	// }

	@Override
	public String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}

}
