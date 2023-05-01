package com.example.mailingservice.service;

import com.example.mailingservice.dto.requests.SendMailRequest;
import com.example.mailingservice.dto.response.SendMailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService implements EmailSender{
    private final JavaMailSender mailSender;

    @Override
    public void sendMail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("abeebahmad24@gmail.com");
        message.setTo(to);
        message.setSubject("Your voting token.");
        message.setText(token);
        mailSender.send(message);
    }
}
