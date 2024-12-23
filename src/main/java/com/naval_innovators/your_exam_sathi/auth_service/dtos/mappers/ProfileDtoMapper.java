package com.naval_innovators.your_exam_sathi.auth_service.dtos.mappers;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileDto;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.models.User;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import com.naval_innovators.your_exam_sathi.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileDtoMapper {
    private final ProfileRepository profileRepository;


    public ProfileDto mapToDto(Long profileId) {
        ProfileDto profileDto = new ProfileDto();
        Profile profileEntity = profileRepository.findById(profileId).orElse(null);
       if (profileEntity == null) {
           throw new RuntimeException("Profile not found");
       }
        profileDto.setFirstName(profileEntity.getFirstName());
        profileDto.setLastName(profileEntity.getLastName());
        profileDto.setAvatarUrl(profileEntity.getAvatarUrl());
        profileDto.setEmail(profileEntity.getUser().getEmail());
        profileDto.setDateOfBirth(profileEntity.getDateOfBirth());
        profileDto.setGender(profileEntity.getGender());
        profileDto.setPhone(profileEntity.getUser().getPhone());
        profileDto.setUserName(profileEntity.getUser().getUserName());
        return profileDto;


    }
}
