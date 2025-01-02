package com.naval_innovators.your_exam_sathi.auth_service.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import jakarta.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class University {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<College> colleges;


    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Profile> profiles;
    
    
    @ManyToMany
    @JoinTable(
        name = "university_course", // Custom join table name
        joinColumns = @JoinColumn(name = "university_id"), // Foreign key for University
        inverseJoinColumns = @JoinColumn(name = "course_id") // Foreign key for Course
    )
    private Set<Course> courses;

}
