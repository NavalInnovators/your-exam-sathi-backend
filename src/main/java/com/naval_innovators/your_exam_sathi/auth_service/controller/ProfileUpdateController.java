package com.naval_innovators.your_exam_sathi.auth_service.controller;


import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileUpdateDto;
import com.naval_innovators.your_exam_sathi.auth_service.service.ProfileUpdateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class ProfileUpdateController {

    @Autowired
    private ProfileUpdateService profileUpdateService;

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ProfileUpdateDto> getUserProfile(@PathVariable long userId){

        ProfileUpdateDto userProfile = profileUpdateService.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);

    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<String> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody @Valid ProfileUpdateDto userProfile,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors.toString());
        }

       profileUpdateService.updateUserProfile(userId, userProfile);
        return ResponseEntity.ok().build();

    }
}
