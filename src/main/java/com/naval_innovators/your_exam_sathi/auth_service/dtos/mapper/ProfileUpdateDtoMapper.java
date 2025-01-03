package com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper;


import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileUpdateDTO;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileUpdateDtoMapper {
    private final ProfileRepository profileRepository;


    public ProfileUpdateDTO mapToDto(Long profileId) {
        ProfileUpdateDTO profileUpdateDto = new ProfileUpdateDTO();
        Profile profileEntity = profileRepository.findById(profileId).orElse(null);
        if (profileEntity == null) {
            throw new RuntimeException("Profile not found");
        }
        profileUpdateDto.setFirstName(profileEntity.getFirstName());
        profileUpdateDto.setLastName(profileEntity.getLastName());
        profileUpdateDto.setAvatarUrl(profileEntity.getAvatarUrl());
        profileUpdateDto.setEmail(profileEntity.getUser().getEmail());
        profileUpdateDto.setDateOfBirth(profileEntity.getDateOfBirth());
        profileUpdateDto.setGender(profileEntity.getGender());
        profileUpdateDto.setPhone(profileEntity.getUser().getPhone());
        profileUpdateDto.setUserName(profileEntity.getUser().getUserName());
        return profileUpdateDto;

    }
}
