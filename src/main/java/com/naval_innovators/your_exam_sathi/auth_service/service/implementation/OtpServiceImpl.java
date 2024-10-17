package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

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

	@Override
	public OtpResponse validateOtp(OtpRequest otpRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateOtp() {
		// TODO Auto-generated method stub
		return null;
	}

}
