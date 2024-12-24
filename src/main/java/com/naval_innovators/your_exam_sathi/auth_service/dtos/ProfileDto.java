package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import com.naval_innovators.your_exam_sathi.auth_service.models.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ProfileDto {
    private String firstName;

    private String lastName;

    private String avatarUrl;

    private boolean isVerified;

    private boolean isPremium;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate dateOfBirth;

    private String userName;

    private String email;

    private String phone;

    int year;



}
