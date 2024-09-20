package com.naval_innovators.your_exam_sathi.auth_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naval_innovators.your_exam_sathi.auth_service.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	boolean existsByEmail(String email);
	boolean existsByUserName(String userName);
	User findByUserName(String userName);
	User findByEmail(String email);
    Optional<User> findByUserNameOrEmail(String userName, String email);


}


