package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.FeedbackDTO;
import com.naval_innovators.your_exam_sathi.auth_service.models.Feedback;

import java.util.List;

public interface FeedbackService {

    Feedback submitFeedback(Long profileId, FeedbackDTO feedbackDTO);

    List<FeedbackDTO> getAllFeedbacks();
}
