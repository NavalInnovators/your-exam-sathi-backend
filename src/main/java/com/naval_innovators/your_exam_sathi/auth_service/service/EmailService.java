package com.naval_innovators.your_exam_sathi.auth_service.service;

import com.naval_innovators.your_exam_sathi.auth_service.models.EmailDetails;

public interface EmailService {
    void sendSimpleMail(EmailDetails details);
}