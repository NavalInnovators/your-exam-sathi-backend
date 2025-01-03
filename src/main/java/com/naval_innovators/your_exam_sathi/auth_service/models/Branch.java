package com.naval_innovators.your_exam_sathi.auth_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "college_branch", // Join table name
            joinColumns = @JoinColumn(name = "branch_id"), // Foreign key for Branch
            inverseJoinColumns = @JoinColumn(name = "college_id") // Foreign key for College
    )
    private Set<College> colleges = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "branch_course", // Custom join table name
        joinColumns = @JoinColumn(name = "branch_id"), // Foreign key for Branch
        inverseJoinColumns = @JoinColumn(name = "course_id") // Foreign key for Course
    )
    private Set<Course> courses;

    // One branch can have multiple Profiles
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Profile> profiles;

}
