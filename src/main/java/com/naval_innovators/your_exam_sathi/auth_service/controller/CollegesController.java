package com.naval_innovators.your_exam_sathi.auth_service.controller;


import com.naval_innovators.your_exam_sathi.auth_service.dtos.CollegeDto;
import com.naval_innovators.your_exam_sathi.auth_service.service.CollegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/college")
public class CollegesController {
    private final CollegeService collegeService;


    @GetMapping("/university/{universityId}")
    public ResponseEntity<List<CollegeDto>> getCollegesByUniversityId(@PathVariable("universityId") Long universityId) {
        List<CollegeDto> colleges = collegeService.getCollegeByUniversityId(universityId);
        return ResponseEntity.ok(colleges);
    }


}
