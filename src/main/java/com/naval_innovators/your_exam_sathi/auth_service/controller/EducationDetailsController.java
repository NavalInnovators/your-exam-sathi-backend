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
//    @PostMapping("/set")
//    public ResponseEntity<Map<String, Object>> setEducationDetails(
//            @PathVariable Long profileId,
//            @Valid @RequestBody EducationDetailsDTO educationDetailsDTO) {
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("set-education-details", "FAILURE");
//
//        try {
//            educationDetailsService.setEducationDetails(educationDetailsDTO, profileId);
//            response.put("set-education-details", "SUCCESS");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            log.error("Error while setting education details for profileId {}: {}", profileId, e.getMessage());
//            response.put("error", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // Return 404 when entity not found
//        }
//    }

    // Update Education Details
    @PutMapping("/update")
    public ResponseEntity<EducationDetailsDTO> updateEducationDetails(
            @PathVariable Long profileId,
            @Valid @RequestBody EducationDetailsDTO educationDetailsDTO) {

        try {
            // Map the DTO to the Profile entity and save the changes
            educationDetailsService.setEducationDetails(educationDetailsDTO, profileId);

            // Return the updated EducationDetailsDTO
            return new ResponseEntity<>(educationDetailsDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("Error while updating education details for profileId {}: {}", profileId, e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 when entity not found
        }
    }

    // Get Education Details
    @GetMapping("/get")
    public ResponseEntity<EducationDetailsDTO> getEducationDetails(@PathVariable Long profileId) {
        try {
            // Fetch the EducationDetailsDTO
            EducationDetailsDTO educationDetailsDTO = educationDetailsService.getEducationDetails(profileId);

            if (educationDetailsDTO != null) {
                return new ResponseEntity<>(educationDetailsDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if no education details found
            }
        } catch (Exception e) {
            log.error("Error while fetching education details for profileId {}: {}", profileId, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 for internal errors
        }
    }

}