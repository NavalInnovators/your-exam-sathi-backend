package com.naval_innovators.your_exam_sathi.auth_service.models;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Indexed;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_tbl",
indexes = {
		@Index(name = "idx_username", columnList = "userName"),
        @Index(name = "idx_email", columnList = "email"),
        @Index(name = "idx_phone",columnList = "phone")
}
)
		
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  	private Long id;

	@Column(nullable = false,unique= true)
	@NotBlank(message = "Username is required")
	private String userName;
	
	@Column(nullable = false, unique = true)
	@NotBlank(message = "Email is required")
    private String email;
	
	@Column(nullable = false,unique=true)
	@NotBlank(message = "Phone is required")
	private String phone;
	
	@Column(nullable = false)
	@NotBlank(message = "Password is required")
	private String password;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	private Profile profile;
}
