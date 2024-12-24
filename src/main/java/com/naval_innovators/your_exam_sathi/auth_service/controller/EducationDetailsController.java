package com.naval_innovators.your_exam_sathi.auth_service.controller;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.EducationDetailsDTO;
import com.naval_innovators.your_exam_sathi.auth_service.service.EducationDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/education-details")
public class EducationDetailsController {

    @Autowired
    private EducationDetailsService educationDetailsService;

    @GetMapping("/{profileId}")
    public ResponseEntity<EducationDetailsDTO> getEducationDetails(@PathVariable Long profileId) {
        return ResponseEntity.ok(educationDetailsService.getEducationDetails(profileId));
    }

    @PostMapping
    public ResponseEntity<Void> saveEducationDetails(@RequestBody EducationDetailsDTO dto) {
        educationDetailsService.saveEducationDetails(dto);
        return ResponseEntity.ok().build();
    }
}
