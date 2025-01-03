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
    public ResponseEntity<FeedbackDTO> submitFeedback(
            @PathVariable Long profileId,
            @Valid @RequestBody FeedbackDTO feedbackDTO) {
        try {
            Feedback feedback = feedbackService.submitFeedback(profileId, feedbackDTO);
            FeedbackDTO responseDTO = FeedbackDTO.builder()
                    .starRating(feedback.getStarRating())
                    .feedback(feedback.getFeedback())
                    .build();
            return ResponseEntity.status(HttpStatus.CREATED).body(null);
        } catch (IllegalArgumentException e) {
            log.error("Error submitting feedback: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            log.error("Unexpected error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GetMapping("/all")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        try {
            List<FeedbackDTO> feedbackDTOs = feedbackService.getAllFeedbacks();
            return ResponseEntity.ok(feedbackDTOs);
        } catch (Exception e) {
            log.error("Error retrieving feedbacks: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
