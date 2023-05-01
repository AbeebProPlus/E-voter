package com.example.mailingservice.controller;

import com.example.mailingservice.service.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

public class EmailSenderController {
    private final EmailSender sender;
    @PostMapping("/send/to/{to}/token/{token}")
    public void sendMail(@PathVariable String to, @PathVariable String token){
        sender.sendMail(to, token);
    }
}