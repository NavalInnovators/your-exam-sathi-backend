package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import com.naval_innovators.your_exam_sathi.auth_service.dtos.ProfileUpdateDTO;
import com.naval_innovators.your_exam_sathi.auth_service.dtos.mapper.ProfileUpdateDtoMapper;
import com.naval_innovators.your_exam_sathi.auth_service.models.Profile;
import com.naval_innovators.your_exam_sathi.auth_service.repository.ProfileRepository;
import com.naval_innovators.your_exam_sathi.auth_service.service.ProfileUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileUpdateServiceImpl implements ProfileUpdateService {

    private final ProfileRepository profileRepository;
    private final ProfileUpdateDtoMapper profileUpdateDtoMapper;

    @Override
    public Boolean updateProfileDetails(Long profileId, ProfileUpdateDTO profileUpdateDto) {
        Optional<Profile> profile = profileRepository.findById(profileId);
        if (profile.isPresent()) {
            Profile profileEntity = profile.get();
            profileEntity.setFirstName(profileUpdateDto.getFirstName().trim());
            profileEntity.setLastName(profileUpdateDto.getLastName().trim());
            profileEntity.setAvatarUrl(profileUpdateDto.getAvatarUrl());
            profileEntity.setDateOfBirth(profileUpdateDto.getDateOfBirth());
            profileEntity.setGender(profileUpdateDto.getGender());
            profileRepository.save(profileEntity);

            return true;
        }
        return false;
    }

    @Override
    public ProfileUpdateDTO getProfile(Long profileId) {
        return profileUpdateDtoMapper.mapToDto(profileId);
    }
}
