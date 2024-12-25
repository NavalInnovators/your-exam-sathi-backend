package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmailOtpRequest {

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email; 

    @NotBlank(message = "Otp is required.")
    @Size(min = 4, max = 4, message = "Otp must be of 6 characters.")
    private String otp;

}
