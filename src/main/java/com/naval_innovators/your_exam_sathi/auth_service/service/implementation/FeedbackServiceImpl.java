package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.FeedbackDTO;
import com.naval_innovators.your_exam_sathi.auth_service.models.Feedback;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.repository.FeedbackRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ProfileRepository profileRepository;

    @Override
    public Feedback submitFeedback(Long profileId, FeedbackDTO feedbackDTO) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Profile not found."));

        Feedback feedback = Feedback.builder()
                .starRating(feedbackDTO.getStarRating())
                .feedback(feedbackDTO.getFeedback() != null ? feedbackDTO.getFeedback() : "")
                .profile(profile)
                .build();

        return feedbackRepository.save(feedback);
    }



    @Override
    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackRepository.findAll()
                .stream()
                .map(feedback -> FeedbackDTO.builder()
                        .starRating(feedback.getStarRating())
                        .feedback(feedback.getFeedback())
                        .build())
                .toList(); //
    }

}
