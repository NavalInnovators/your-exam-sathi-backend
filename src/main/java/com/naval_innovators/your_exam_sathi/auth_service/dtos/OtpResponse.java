package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpResponse {

	private String message;
	private Map<String,String> body;

}
