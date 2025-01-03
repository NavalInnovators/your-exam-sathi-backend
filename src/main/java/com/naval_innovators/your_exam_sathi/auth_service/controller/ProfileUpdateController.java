package com.naval_innovators.your_exam_sathi.auth_service.controller;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileUpdateDTO;
import com.naval_innovators.your_exam_sathi.auth_service.service.ProfileService;
import com.naval_innovators.your_exam_sathi.auth_service.service.ProfileUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/profile/{profileId}")
public class ProfileUpdateController {

    private final ProfileUpdateService profileUpdateService;

    @GetMapping
    public ResponseEntity<ProfileUpdateDTO> getProfile(@PathVariable Long profileId) {
        try {
            ProfileUpdateDTO profileUpdateDto = profileUpdateService.getProfile(profileId);
            if (profileUpdateDto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(profileUpdateDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(
            @PathVariable Long profileId,
            @Valid @RequestBody ProfileUpdateDTO profileUpdateDto) {
        try {
            boolean isUpdated = profileUpdateService.updateProfileDetails(profileId, profileUpdateDto);
            if (isUpdated) {
                return ResponseEntity.ok()
                        .body("Profile Updated Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Profile not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred");
        }
    }
}
