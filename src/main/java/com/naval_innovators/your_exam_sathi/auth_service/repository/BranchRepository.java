package com.naval_innovators.your_exam_sathi.auth_service.repository;

import com.naval_innovators.your_exam_sathi.auth_service.models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    Optional<Branch> findByName(String name);

}
