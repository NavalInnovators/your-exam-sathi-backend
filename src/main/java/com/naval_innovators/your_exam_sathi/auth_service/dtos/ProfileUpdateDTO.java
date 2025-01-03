package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import com.naval_innovators.your_exam_sathi.auth_service.models.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ProfileUpdateDTO {

    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Date of Birth cannot be null")
    @Past(message = "Date of Birth must be a past date")
    private LocalDate dateOfBirth;
//
//    private String userName;
//
//    private String email;
//
//    private String phone;

}
