package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import com.naval_innovators.your_exam_sathi.auth_service.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String username;

    @NotBlank
    private LocalDate dateOfBirth;
    private String gender;

    @Email
    private String email;

    @NotBlank
    private String phone;

    public UserProfile(User user) {
        this.firstName = user.getProfile().getFirstName();
        this.lastName = user.getProfile().getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.dateOfBirth = user.getProfile().getDateOfBirth();
    }
}
