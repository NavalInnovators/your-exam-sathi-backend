package com.naval_innovators.your_exam_sathi.auth_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_query")
public class UserQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queryId;

    @Column(nullable = false)
    private String query;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate submissionDate;

    @Column(nullable = false, columnDefinition = "TEXT DEFAULT ''")
    private String answer;
}
