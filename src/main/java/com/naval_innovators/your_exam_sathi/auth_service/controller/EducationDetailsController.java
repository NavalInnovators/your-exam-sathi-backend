package com.naval_innovators.your_exam_sathi.auth_service.controller;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.EducationDetailsDTO;
import com.naval_innovators.your_exam_sathi.auth_service.service.EducationDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/profile/{profileId}/education")
public class EducationDetailsController {

    private final EducationDetailsService educationDetailsService;

    // Set Education Details
    @PostMapping("/set")
    public ResponseEntity<Map<String, Object>> setEducationDetails(
            @PathVariable Long profileId,
            @Valid @RequestBody EducationDetailsDTO educationDetailsDTO) {

        Map<String, Object> response = new HashMap<>();
        response.put("set-education-details", "FAILURE");

        try {
            educationDetailsService.setEducationDetails(educationDetailsDTO, profileId);
            response.put("set-education-details", "SUCCESS");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("Error while setting education details for profileId {}: {}", profileId, e.getMessage());
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Return 404 when entity not found
        }
    }

    // Update Education Details
    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateEducationDetails(
            @PathVariable Long profileId,
            @Valid @RequestBody EducationDetailsDTO educationDetailsDTO) {

        Map<String, Object> response = new HashMap<>();
        response.put("update-education-details", "FAILURE");

        try {
            educationDetailsService.setEducationDetails(educationDetailsDTO, profileId);
            response.put("update-education-details", "SUCCESS");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("Error while updating education details for profileId {}: {}", profileId, e.getMessage());
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Return 404 when entity not found
        }
    }

    // Get Education Details
    @GetMapping("/get")
    public ResponseEntity<Map<String, Object>> getEducationDetails(@PathVariable Long profileId) {
        Map<String, Object> response = new HashMap<>();
        response.put("education-details", "NO EDUCATION DETAILS FOUND");

        try {
            EducationDetailsDTO educationDetailsDTO = educationDetailsService.getEducationDetails(profileId);
            response.put("education-details", educationDetailsDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while fetching education details for profileId {}: {}", profileId, e.getMessage());
            response.put("error", "Error while fetching education details");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
