package com.naval_innovators.your_exam_sathi.auth_service.controller;

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
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/course/{courseId}/enroll")
    public ResponseEntity<Map<String, Boolean>> enrollToCourse(@PathVariable long profileId, @PathVariable long courseId) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("enrolled", false);
        if (profileService.enrollToCourse(profileId,courseId)){
            response.put("enrolled", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
