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
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queryId;

    @Column(nullable = false)
    private String query;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)  
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) 
    private User user;

    @Column(nullable = false)
    private LocalDate submissionDate;
}