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
    public Feedback submitFeedback(FeedbackDTO feedbackDTO) {
        Profile profile = profileRepository.findById(feedbackDTO.getProfileId())
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
        // **Changed**: Mapping Feedback entities to FeedbackDTOs
        return feedbackRepository.findAll()
                .stream()
                .map(feedback -> FeedbackDTO.builder()
                        .starRating(feedback.getStarRating()) // **Changed**: Map starRating
                        .feedback(feedback.getFeedback()) // **Changed**: Map feedback text
                        .profileId(feedback.getProfile().getId()) // **Changed**: Map profileId
                        .build())
                .toList(); // **Changed**: Convert to List<FeedbackDTO>
    }

}
