package com.naval_innovators.your_exam_sathi.auth_service.repository;

import com.naval_innovators.your_exam_sathi.auth_service.models.College;
import com.naval_innovators.your_exam_sathi.auth_service.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CollegeRepository extends JpaRepository<College, Long> {

//    @Query("SELECT c FROM College c WHERE c.name = :name")
//    Optional<College> findByName(@Param("name") String name);
//
//    @Query("SELECT c FROM College c WHERE c.university.id = :universityId")
//    Optional<College> findByUniversityId(@Param("universityId") Long universityId);

}
