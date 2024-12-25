package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.FeedbackDTO;
import com.naval_innovators.your_exam_sathi.auth_service.models.Feedback;
import com.naval_innovators.your_exam_sathi.auth_service.models.User;
import com.naval_innovators.your_exam_sathi.auth_service.repository.FeedbackRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UserRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Feedback submitFeedback(FeedbackDTO feedbackDTO) {

        if (feedbackDTO.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        // Find the user by ID
        User user = userRepository.findById(feedbackDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create a feedback entity
        Feedback feedback = Feedback.builder()
                .textFeedback(feedbackDTO.getTextFeedback())
                .user(user)
                .build();

        // Save feedback
        return feedbackRepository.save(feedback);
    }
}
