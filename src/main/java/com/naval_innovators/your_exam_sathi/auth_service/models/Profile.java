
package com.naval_innovators.your_exam_sathi.auth_service.models;

import java.time.LocalDate;
import java.util.Set;

import com.naval_innovators.your_exam_sathi.auth_service.models.enums.Gender;

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
@Table(name = "profile_tbl")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "avatar_url", length = 512)
	private String avatarUrl;

	@Column(name = "is_verified", nullable = false)
	private boolean isVerified;

	@Column(name = "is_premium", nullable = false)
	private boolean isPremium;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", length = 20)
	private Gender gender;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "college_id")
	private College college;

	@ManyToOne
	@JoinColumn(name = "branch_id", nullable = false)
	private Branch branch;

	@ManyToMany
	@JoinTable(
			name = "profile_courses", // Join table name
			joinColumns = @JoinColumn(name = "profile_id"), // Foreign key for Profile
			inverseJoinColumns = @JoinColumn(name = "course_id") // Foreign key for Course
	)
	private Set<Course> courses;
}
