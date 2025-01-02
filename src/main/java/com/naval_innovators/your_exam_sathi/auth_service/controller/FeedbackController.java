package com.naval_innovators.your_exam_sathi.auth_service.controller;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.FeedbackDTO;
import com.naval_innovators.your_exam_sathi.auth_service.models.Feedback;
import com.naval_innovators.your_exam_sathi.auth_service.service.FeedbackService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/submit/{profileId}")
    public ResponseEntity<Map<String, Object>> submitFeedback(
            @PathVariable Long profileId,
            @Valid @RequestBody FeedbackDTO feedbackDTO) {

        Map<String, Object> response = new HashMap<>();
        try {

            feedbackDTO.setProfileId(profileId);
            Feedback feedback = feedbackService.submitFeedback(feedbackDTO);
            response.put("message", "Feedback submitted successfully!");
            response.put("feedback", feedback);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            response.put("error", "An unexpected error occurred.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllFeedbacks() {
        Map<String, Object> response = new HashMap<>();
        try {
            // Call the updated service method to get DTOs
            List<FeedbackDTO> feedbackDTOs = feedbackService.getAllFeedbacks();
            response.put("feedbacks", feedbackDTOs);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error retrieving feedbacks: " + e.getMessage());
            response.put("error", "An error occurred while fetching feedback.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
