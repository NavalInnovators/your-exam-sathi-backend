package com.naval_innovators.your_exam_sathi.auth_service.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.naval_innovators.your_exam_sathi.auth_service.models.Role;
import com.naval_innovators.your_exam_sathi.auth_service.models.enums.RoleName;
import com.naval_innovators.your_exam_sathi.auth_service.repository.RolesRepository;


//@Component
public class DataInitializer implements CommandLineRunner {

    private final RolesRepository roleRepository;

    public DataInitializer(RolesRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName(RoleName.ROLE_USER).isEmpty()) {
            Role roleUser = new Role();
            roleUser.setName(RoleName.ROLE_USER);
            roleRepository.save(roleUser);
        }

        if (roleRepository.findByName(RoleName.ROLE_ADMIN).isEmpty()) {
            Role roleAdmin = new Role();
            roleAdmin.setName(RoleName.ROLE_ADMIN);
            roleRepository.save(roleAdmin);
        }

        if (roleRepository.findByName(RoleName.ROLE_PREMIUM).isEmpty()) {
            Role rolePremium = new Role();
            rolePremium.setName(RoleName.ROLE_PREMIUM);
            roleRepository.save(rolePremium);
        }
    }
}

