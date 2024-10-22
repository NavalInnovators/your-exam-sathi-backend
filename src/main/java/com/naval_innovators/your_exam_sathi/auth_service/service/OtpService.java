package com.naval_innovators.your_exam_sathi.auth_service.service;
import org.springframework.stereotype.Service;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.OtpRequest;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.OtpResponse;

@Service
public interface OtpService {
	public void sendOtp(String phone);
	public String validateOtp(OtpRequest otpRequest);
	public String generateOtp();
}
