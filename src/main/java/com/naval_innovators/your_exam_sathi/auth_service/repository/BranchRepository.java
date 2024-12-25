package com.naval_innovators.your_exam_sathi.auth_service.repository;

import com.naval_innovators.your_exam_sathi.auth_service.models.Branch;
import com.naval_innovators.your_exam_sathi.auth_service.models.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {

//    @Query("SELECT b FROM Branch b WHERE b.name = :name")
//    Optional<Branch> findByName(@Param("name") String name);
//
//    @Query("SELECT b FROM Branch b WHERE b.colleges.id = :collegeId")
//    Optional<Branch> findByCollegeId(@Param("collegeId") Long collegeId);

}
