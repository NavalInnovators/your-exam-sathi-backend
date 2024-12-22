package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.FeedbackDTO;
import com.naval_innovators.your_exam_sathi.auth_service.models.Feedback;

public interface FeedbackService {
    /**
     * Submit feedback for a user.
     *
     * @param feedbackDTO The feedback details from the client.
     * @return Feedback object if saved successfully.
     * @throws IllegalArgumentException if the user is not found.
     */
    Feedback submitFeedback(FeedbackDTO feedbackDTO);
}
