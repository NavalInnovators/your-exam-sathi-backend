package com.naval_innovators.your_exam_sathi.auth_service.controller;


import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileDto;
import com.naval_innovators.your_exam_sathi.auth_service.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/profile/{profileId}")
public class ProfileUpdateController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getProfile(@PathVariable Long profileId) {
        Map<String, Object> response = new HashMap<>();
        try {
            ProfileDto profileDto = profileService.getProfile(profileId);
            if (profileDto == null) {
                response.put("profile", "NO PROFILE FOUND");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            response.put("profile", profileDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("profile", "ERROR: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String,Object>> updateProfile(
            @PathVariable Long profileId,
            @RequestBody ProfileDto profileDto){
        Map<String, Object> response = new HashMap<>();

        try{
            if(profileService.setProfileDetails(profileId, profileDto)){
                response.put("update-profile", "SUCCESS");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else{
                response.put("update-profile", "FAILED");
                response.put("error", "Profile not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            response.put("update-profile", "FAILED");
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
}
