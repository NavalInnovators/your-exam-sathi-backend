package com.naval_innovators.your_exam_sathi.auth_service.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackDTO {

    private String textFeedback;

    @NotNull(message = "User ID must not be null")
    private Long userId;
}
