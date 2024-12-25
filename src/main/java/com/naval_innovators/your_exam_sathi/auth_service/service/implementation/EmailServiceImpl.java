package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import com.naval_innovators.your_exam_sathi.auth_service.models.EmailDetails;
import com.naval_innovators.your_exam_sathi.auth_service.service.EmailService;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${spring.mail.username}")
    private String sender;

    private static final String FIXED_SUBJECT = "Email Verification for Your Exam Sathi";

    private static final String FIXED_MSG_BODY_TEMPLATE = """
            Hi %s,

            Welcome to Your Exam Sathi! 🎉

            To complete your registration, please verify your email address by using the following OTP code:

            🔑 OTP: %s

            This OTP will expire in 10 minutes. If you did not request this, please ignore this message.

            Thank you,
            Team Your Exam Sathi
            """;

    @Override
    public void sendVerificationMail(EmailDetails details) {
        try {
            if (details.getRecipient() == null || details.getRecipient().isEmpty()) {
                throw new IllegalArgumentException("Recipient email address is missing.");
            }

            String otp = generateOtp();
            logger.info("Generated OTP: {}", otp); 

            // Store the OTP in Redis with a 10-minute expiry time
            redisTemplate.opsForValue().set(details.getRecipient(), otp, 10, TimeUnit.MINUTES);

            String storedOtp = redisTemplate.opsForValue().get(details.getRecipient());
            logger.info("Retrieved OTP from Redis for {}: {}", details.getRecipient(), storedOtp);

            String personalizedMessage = String.format(FIXED_MSG_BODY_TEMPLATE, details.getUsername(), otp);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setSubject(FIXED_SUBJECT);
            mimeMessageHelper.setText(personalizedMessage);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while sending email!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unexpected error occurred while sending email!");
        }
    }

    private String generateOtp() {
        int otp = (int)(Math.random() * 10000);
        return String.format("%04d", otp); 
    }
}
