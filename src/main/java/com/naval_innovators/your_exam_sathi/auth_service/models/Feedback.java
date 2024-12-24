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

    @Column(nullable = false)
    private String textFeedback;

    @ManyToOne(fetch = FetchType.LAZY) // Many feedbacks can belong to one user
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
