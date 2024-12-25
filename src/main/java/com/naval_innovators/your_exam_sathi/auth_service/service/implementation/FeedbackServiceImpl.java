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
<<<<<<< HEAD
        Profile profile = profileRepository.findById(feedbackDTO.getProfileId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found."));

        // Ensure profile is linked with a user
        if (profile.getUser() == null) {
            throw new IllegalArgumentException("Profile is not associated with a user.");
        }

        Feedback feedback = Feedback.builder()
                .starRating(feedbackDTO.getStarRating())
                .feedback(feedbackDTO.getFeedback() != null ? feedbackDTO.getFeedback() : "")
=======
        if (feedbackDTO.getProfileId() == null) {
            throw new IllegalArgumentException("Profile ID must not be null.");
        }

        Profile profile = profileRepository.findById(feedbackDTO.getProfileId())
                .orElseThrow(() -> new IllegalArgumentException("Profile not found."));

        if (feedbackDTO.getStarRating() == null || feedbackDTO.getStarRating() < 1 || feedbackDTO.getStarRating() > 5) {
            throw new IllegalArgumentException("Star rating must be between 1 and 5.");
        }

        Feedback feedback = Feedback.builder()
                .starRating(feedbackDTO.getStarRating())
                .textFeedback(feedbackDTO.getTextFeedback())
>>>>>>> e4b042239cb611862202ed8ebade893a153f6eca
                .profile(profile)
                .build();

        return feedbackRepository.save(feedback);
    }

<<<<<<< HEAD


=======
>>>>>>> e4b042239cb611862202ed8ebade893a153f6eca
    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> e4b042239cb611862202ed8ebade893a153f6eca
