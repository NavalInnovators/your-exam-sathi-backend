package com.naval_innovators.your_exam_sathi.auth_service.controller;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.SyllabusResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FastAPIController {

    private final String SYLLABUS_API_URL = "https://ai-utils.onrender.com/getSyllabus";

    @GetMapping("/getSyllabus/{subcode}")
    public ResponseEntity<?> getSyllabus(@PathVariable String subcode) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String apiUrlWithParams = SYLLABUS_API_URL + "?subcode=" + subcode;
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrlWithParams,
                    HttpMethod.GET,
                    entity,
                    String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                SyllabusResponse syllabusResponse = new SyllabusResponse();
                syllabusResponse.setSyllabus(response.getBody());
                return ResponseEntity.ok(syllabusResponse);
            } 
            else {
                return ResponseEntity.status(response.getStatusCode())
                        .body("Error: Unable to fetch syllabu.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
        }
    }
}
