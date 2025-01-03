package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.FeedbackDTO;
import com.naval_innovators.your_exam_sathi.auth_service.models.Feedback;

import java.util.List;

public interface FeedbackService {
    /**
     * Submit feedback for a profile.
     *
     * @param feedbackDTO The feedback details from the client.
     * @return Feedback object if saved successfully.
     * @throws IllegalArgumentException if the profile is not found.
     */
    Feedback submitFeedback(FeedbackDTO feedbackDTO);

    /**
     * Get all feedbacks that are public.
     *
     * @return List of all feedbacks.
     */
    List<FeedbackDTO> getAllFeedbacks();
}
