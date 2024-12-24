package com.naval_innovators.your_exam_sathi.auth_service.models;

import jakarta.validation.constraints.Size;

// Java Program to Illustrate EmailDetails Class

// Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDetails {

    @NotBlank(message = "Recipient is required")  
    @Email(message = "Please provide a valid email address")  
    private String recipient;

    @NotBlank(message = "Username is required")
    private String username;
}
