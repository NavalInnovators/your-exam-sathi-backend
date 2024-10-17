package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.OtpRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
	private final static int MAX_TIME_LIMIT = 5;
	private final RedisTemplate<String, String> redisTemplate;
	private final  BCryptPasswordEncoder passwordEncoder ;

	public void saveOtp(String phone, String otp) {
		String hashedOtp = passwordEncoder.encode(otp);
		redisTemplate.opsForValue().set(phone, hashedOtp);
	}

	public String getOtp(String phone) {
		return redisTemplate.opsForValue().get(phone);
	}

	public void deletOtp(String phone) {
		redisTemplate.delete(phone);
	}

	public boolean validateOtp(OtpRequest otpRequest) {
		String storedHashedOtp = redisTemplate.opsForValue().get(otpRequest.getPhone());
		if (storedHashedOtp != null && passwordEncoder.matches(otpRequest.getOtp(), storedHashedOtp)) {
			deletOtp(otpRequest.getPhone());
			return true;
		}
		return false;
	}

	public void saveJwt(String email, String jwt) {
		redisTemplate.opsForValue().set(email, jwt);
	}

	public String getJwt(String email) {
		return redisTemplate.opsForValue().get(email);
	}
	public void deleteJwt(String email) {
		redisTemplate.delete(email);
	}
	
}
