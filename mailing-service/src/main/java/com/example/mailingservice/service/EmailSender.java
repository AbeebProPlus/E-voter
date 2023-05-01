package com.example.mailingservice.service;

import com.example.mailingservice.dto.requests.SendMailRequest;
import com.example.mailingservice.dto.response.SendMailResponse;

public interface EmailSender {
    void sendMail(String to, String token);
}
