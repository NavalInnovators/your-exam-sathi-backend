package com.naval_innovators.your_exam_sathi.auth_service.models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

//    @Column(nullable = false)
//    private String university;
//
//    @Column(nullable = false, unique = true)
//    private String courseCode;
//
//    @Column(nullable = false)
//    private String branch;

    @Column(nullable = false)
    private Integer year;
    
    @ManyToMany(mappedBy = "courses") // Mapped by the 'courses' field in Branch
    private Set<Branch> branches;
    
    @OneToMany(mappedBy = "course",cascade =  CascadeType.ALL,orphanRemoval = true)
    private Set<CourseCode> courseCodes;
    
    @ManyToMany(mappedBy = "courses")
    private Set<University> universities;
}
