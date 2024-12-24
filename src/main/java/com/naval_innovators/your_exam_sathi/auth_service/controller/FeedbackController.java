package com.naval_innovators.your_exam_sathi.auth_service.controller;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.FeedbackDTO;
import com.naval_innovators.your_exam_sathi.auth_service.models.Feedback;
import com.naval_innovators.your_exam_sathi.auth_service.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<String> submitFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) {
        try {
            Feedback feedback = feedbackService.submitFeedback(feedbackDTO);
            return ResponseEntity.ok("Feedback submitted successfully with ID: " + feedback.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
