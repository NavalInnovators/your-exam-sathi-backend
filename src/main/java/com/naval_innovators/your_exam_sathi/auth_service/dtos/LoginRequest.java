package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
	private String userName;

    private String email;
	
	@NotBlank(message = "Password is required")
	@Size(min=6,message ="Password must be at least 6 characters")
	private String password;
	
	public boolean isValid() {
        return (userName != null && !userName.isEmpty()) || (email != null && !email.isEmpty());
    }
}
