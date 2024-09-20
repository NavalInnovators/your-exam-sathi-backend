package com.naval_innovators.your_exam_sathi.auth_service.models;
import java.time.LocalDate;
import java.util.UUID;

import com.naval_innovators.your_exam_sathi.auth_service.models.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
	
	@Column(nullable = false)
	private String firstName;

	private String lastName;

	@Column(nullable = false)
	private String phoneNumber;

	@Column(length = 512)
	private String avatarUrl;

	@Column(nullable = false)
	private boolean isVerified;

	@Column(nullable = false)
	private boolean isPremium;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Gender gender;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@OneToOne
	@JoinColumn(name ="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "college_id")
	private College college;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
}
