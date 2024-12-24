package com.naval_innovators.your_exam_sathi.auth_service.service.implementation;

import org.springframework.stereotype.Service;
import com.naval_innovators.your_exam_sathi.auth_service.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import com.naval_innovators.your_exam_sathi.auth_service.models.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    private static final String FIXED_SUBJECT = "Welcome to Your Exam Sathi!";
    private static final String FIXED_MSG_BODY_TEMPLATE = """
            Dear %s,
            
            Congratulations! Your registration with Exam Sathi is successful. You can now access all the tools and resources to prepare for your exams effectively.
            
            Log in at [Website URL] and start your journey today!
            
            For any assistance, contact us at [support email].
            
            Best regards,
            Exam Sathi Team
            """;

    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {
            // Validate recipient email
            if (details.getRecipient() == null || details.getRecipient().isEmpty()) {
                return "Your email address not found, please check it again.";
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
            return "Mail Sent Successfully";
        } 
        catch (MessagingException e) {
            e.printStackTrace();
            return "Error while sending mail!";
        } 
        catch (Exception e) {
            e.printStackTrace();
            return "Error while sending mail!";
        }
    }
}

