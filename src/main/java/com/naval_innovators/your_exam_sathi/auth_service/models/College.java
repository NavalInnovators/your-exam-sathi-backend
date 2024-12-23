package com.naval_innovators.your_exam_sathi.auth_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "colleges") // Inverse side of the Many-to-Many relationship
    private Set<Branch> branches;

    @ManyToOne
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    // One college can have multiple Profiles
    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Profile> profiles;
}
