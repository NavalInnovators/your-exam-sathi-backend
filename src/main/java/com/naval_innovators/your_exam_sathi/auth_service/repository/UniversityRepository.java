package com.naval_innovators.your_exam_sathi.auth_service.repository;

import com.naval_innovators.your_exam_sathi.auth_service.models.University;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Long> {

//    @Query("SELECT u FROM University u WHERE u.name = :name")
//    Optional<University> findByName(@Param("name") String name);

}
