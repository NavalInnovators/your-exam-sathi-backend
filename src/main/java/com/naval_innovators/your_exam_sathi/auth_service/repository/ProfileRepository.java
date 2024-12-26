package com.naval_innovators.your_exam_sathi.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import java.util.Optional;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    
    @Query("SELECT p FROM Profile p JOIN FETCH p.courses WHERE p.user.id = :profileId")
    Optional<Profile> findByUserIdWithCourses(Long profileId);

    @Query("SELECT p FROM Profile p WHERE p.user.id = :userId")
    Profile findByUserId(Long userId);
}
