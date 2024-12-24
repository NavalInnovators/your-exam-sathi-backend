package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import org.springframework.stereotype.Service;
import com.naval_innovators.your_exam_sathi.auth_service.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import com.naval_innovators.your_exam_sathi.auth_service.models.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private static final String FIXED_SUBJECT = "Welcome to Your Exam Sathi – Personalized Learning Awaits!";
    private static final String FIXED_MSG_BODY_TEMPLATE = """
            Hi %s,

            Welcome to Your Exam Sathi, your AI-powered study companion! 🎉

            We’re excited to have you join a platform that’s designed to make your exam preparation smarter, faster, and more effective.

            💡 Features tailored specially for you 🐈
            Our platform leverages cutting-edge AI technology to:
            ✅ Analyze your strengths and areas for improvement.
            ✅ Personalize study plans just for you.
            ✅ Recommend the best resources and practice tests tailored to your goals.

            Get started today and unlock a seamless learning experience!

            👉 Log in to Your Dashboard

            If you have any questions or need help, feel free to reach out to us anytime.

            Here’s to your success! 🚀
            Team: Your Exam Sathi
            """;

    @Override
    public void sendSimpleMail(EmailDetails details) {
        try {
            // Validate recipient email
            if (details.getRecipient() == null || details.getRecipient().isEmpty()) {
                throw new IllegalArgumentException("Recipient email address is missing.");
            }

            // Personalize the message
            String personalizedMessage = String.format(FIXED_MSG_BODY_TEMPLATE, details.getUsername());

            // Create the email
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setSubject(FIXED_SUBJECT);
            mimeMessageHelper.setText(personalizedMessage);

            // Send email
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while sending mail!");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unexpected error occurred while sending mail!");
        }
    }
}
