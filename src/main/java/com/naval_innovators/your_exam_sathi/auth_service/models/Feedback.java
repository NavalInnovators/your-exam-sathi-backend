package com.naval_innovators.your_exam_sathi.auth_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "feedback_tbl")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(name = "feedback") // Made nullable to indicate it's optional
    private String feedback;
=======
    @Column(name = "feedback", nullable = false)
    private String textFeedback;
>>>>>>> e4b042239cb611862202ed8ebade893a153f6eca

    @Column(name = "star_rating", nullable = false)
    private Integer starRating;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false) // Enforce non-null profile mapping
=======
    @ManyToOne
    @JoinColumn(name = "profile_id")
>>>>>>> e4b042239cb611862202ed8ebade893a153f6eca
    private Profile profile;
}

