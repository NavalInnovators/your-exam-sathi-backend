package com.naval_innovators.your_exam_sathi.auth_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naval_innovators.your_exam_sathi.auth_service.models.Role;
import com.naval_innovators.your_exam_sathi.auth_service.models.enums.RoleName;



public interface RolesRepository  extends JpaRepository<Role, Long>{
    Optional<Role> findByName(RoleName roleName);
}
