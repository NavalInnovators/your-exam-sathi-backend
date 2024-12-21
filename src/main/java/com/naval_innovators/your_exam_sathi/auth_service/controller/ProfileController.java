package com.naval_innovators.your_exam_sathi.auth_service.controller;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.UserProfile;
import com.naval_innovators.your_exam_sathi.auth_service.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")

public class ProfileController {
    @Autowired
    private UserServices userServices;

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable long userId){

        UserProfile userProfile = userServices.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);

    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<Void> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody UserProfile userProfile
    ){

        userServices.updateUserProfile(userId, userProfile);
        return ResponseEntity.ok().build();

    }

    @PatchMapping("/profile/{userId}")
    public ResponseEntity<Void> partiallyUpdateUserProfile(
            @PathVariable Long userId,
            @RequestBody UserProfile partialUpdateProfile
    ) {

        userServices.updateUserProfile(userId, partialUpdateProfile);
        return ResponseEntity.ok().build();

    }
}
