package com.naval_innovators.your_exam_sathi.auth_service.repository;

import com.naval_innovators.your_exam_sathi.auth_service.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
