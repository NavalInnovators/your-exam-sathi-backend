package com.naval_innovators.your_exam_sathi.auth_service.repository;

import com.naval_innovators.your_exam_sathi.auth_service.models.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<Query, Long> {
}
