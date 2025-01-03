package com.naval_innovators.your_exam_sathi.auth_service.dtos.responseDTOs;

import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private String accessToken;
    private Long profileId;
}
