package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OtpRequest {
	@NotBlank(message = "Otp is required.")
	@Size(min = 10,max=10,message = "Otp must be of 6 characters.")
	private String phone;
	
	@NotBlank(message = "Otp is required.")
	@Size(min = 6,max=6,message = "Otp must be of 6 characters.")
	private String otp;
	
}
