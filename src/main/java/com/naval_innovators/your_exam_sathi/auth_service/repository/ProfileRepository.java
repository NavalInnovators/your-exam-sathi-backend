package com.naval_innovators.your_exam_sathi.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
