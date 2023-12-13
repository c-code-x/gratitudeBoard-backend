package com.Studentlifr.GratitudeBoard.services.impl;

import com.Studentlifr.GratitudeBoard.dto.response.BasicResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.GenericResDTO;
import com.Studentlifr.GratitudeBoard.dto.response.ResponseDTO;
import com.Studentlifr.GratitudeBoard.exceptions.ApiRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    private final String senderEmailId;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, @Value("${spring.mail.username}") String senderEmailId) {
        this.javaMailSender = javaMailSender;
        this.senderEmailId = senderEmailId;
    }

    public ResponseDTO sendEmail(String toEmailId, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmailId);
            message.setTo(toEmailId);
            message.setSubject(subject);
            message.setText(body);
            javaMailSender.send(message);
            return new ResponseDTO( new BasicResDTO("Email sent successfully", HttpStatus.OK));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiRuntimeException("Error while sending email", HttpStatus.BAD_REQUEST);
        }
    }
}
