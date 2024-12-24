package com.naval_innovators.your_exam_sathi.auth_service.repository;

import com.naval_innovators.your_exam_sathi.auth_service.models.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UniversityRepository extends JpaRepository<University, Long> {

    Optional<University> findByName(String name);

}
