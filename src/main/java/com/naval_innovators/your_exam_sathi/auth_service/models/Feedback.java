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

    @Column(name = "feedback", nullable = false)
    private String textFeedback;

    @Column(name = "star_rating", nullable = false)
    private Integer starRating;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}

