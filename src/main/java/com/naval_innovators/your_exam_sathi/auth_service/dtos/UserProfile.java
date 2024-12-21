package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import com.naval_innovators.your_exam_sathi.auth_service.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @NotEmpty(message = "First Name is required")
    @NotNull(message = "First Name cannot be empty")
    private String firstName;

    @NotEmpty(message = "Last Name is required")
    @NotNull(message = "Last Name cannot be empty")
    private String lastName;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    private LocalDate dateOfBirth;
    private String gender;

    @Email
    @NotEmpty(message = "Email is required")
    @NotNull(message = "Email cannot bee empty")
    private String email;

    private String phone;

    public UserProfile(User user) {
        this.firstName = user.getProfile().getFirstName();
        this.lastName = user.getProfile().getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.dateOfBirth = user.getProfile().getDateOfBirth();
    }
}
