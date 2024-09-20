package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {
	
	@NotBlank(message = "FirstName is required")
	@Size(min=2,message = "FirstName must be at least 2 characters")
	private String firstName;
	
	@NotBlank(message = "LastName is required")
	@Size(min=2,message = "LastName must be at least 2 characters")
	private String lastName;
	
	@NotBlank(message = "Phone Number is required")
	@Size(min=10,message = "Phone Number must be 10 characters")
	private String phone;

	@NotBlank(message = "UserName is required")
	@Size(min=5,message = "UserName must be at least 5 characters")
	private String userName;
	
	@NotBlank(message = "Password is required")
	@Size(min=6,message ="Password must be at least 6 characters")
	private String password;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;
}
