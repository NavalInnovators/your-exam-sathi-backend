package com.naval_innovators.your_exam_sathi.auth_service.controller;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.CourseResponse;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.ExtraDetailsDto;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileDto;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.responseDTOs.AppResponse;
import com.naval_innovators.your_exam_sathi.auth_service.models.Course;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/profile/{profileId}")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/course/{courseId}/enroll")
    public ResponseEntity<AppResponse> enrollToCourse(@PathVariable long profileId, @PathVariable long courseId) {
        AppResponse response = new AppResponse();

        if (profileService.enrollToCourse(profileId,courseId)){
            response.setResponse(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getCoursesByUserId(@PathVariable Long profileId) {
        try{
            List<CourseResponse> response = profileService.getCoursesByProfileId(profileId);
            return ResponseEntity.ok(response);
        } 
        catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @GetMapping("/get")
    public ResponseEntity<Map<String,Object>> getProfile(@PathVariable Long profileId) {
        Map<String, Object> response = new HashMap<>();
        response.put("profile", "NO PROFILE FOUND");
        try {
            ProfileDto profileDto = profileService.getProfile(profileId);
            response.put("profile", profileDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/set")
    public ResponseEntity<AppResponse> setProfile(@PathVariable Long profileId, @RequestBody ProfileDto profileDto) {
        AppResponse response = new AppResponse();


        if (profileService.setProfileDetails(profileId, profileDto)) {
            response.setResponse(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/set-details")
    public ResponseEntity<AppResponse> setProfileDetails(@PathVariable Long profileId, @RequestBody ExtraDetailsDto extraDetailsDto) {
        AppResponse response = new AppResponse();

        try {
            if (profileService.setExtraDetails(profileId, extraDetailsDto)) {
                response.setResponse(true);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception e) {
            //logging the error in terminal
            log.error(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
