package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileUpdateDto;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.models.User;
import com.naval_innovators.your_exam_sathi.auth_service.models.enums.Gender;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UserRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.ProfileUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProfileUpdateServiceImpl implements ProfileUpdateService {

    private final UserRepository userRepository;

    @Override
    public ProfileUpdateDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + userId));

        return new ProfileUpdateDto(user);
    }

    @Override
    public void updateUserProfile(Long userId, ProfileUpdateDto updateProfile) {

        if (updateProfile == null || updateProfile.getFirstName() == null || updateProfile.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First Name is required");
        }
        if (updateProfile.getLastName() == null || updateProfile.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last Name is required");
        }
        if (updateProfile.getEmail() == null || updateProfile.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with ID: " + userId));

        Profile profile = user.getProfile();
        profile.setFirstName(updateProfile.getFirstName());
        profile.setLastName(updateProfile.getLastName());
        profile.setDateOfBirth(updateProfile.getDateOfBirth());

        if (updateProfile.getUsername() != null ) {
            user.setUserName(updateProfile.getUsername());
        }
        if (updateProfile.getEmail() != null) {
            user.setEmail(updateProfile.getEmail());
        }
        if (updateProfile.getPhone() != null && !updateProfile.getGender().isEmpty()) {
            user.setPhone(updateProfile.getPhone());
        }

        if (updateProfile.getGender() != null && !updateProfile.getGender().isEmpty()) {
            try {
                Gender genderEnum = Gender.valueOf(updateProfile.getGender().toUpperCase());
                profile.setGender(genderEnum);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid Gender value");
            }
        }
        userRepository.save(user);
    }

}
