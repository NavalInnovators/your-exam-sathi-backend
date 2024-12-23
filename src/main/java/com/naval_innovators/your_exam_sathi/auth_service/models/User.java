package com.naval_innovators.your_exam_sathi.auth_service.models;

import jakarta.persistence.*;

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
