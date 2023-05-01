package com.example.registrationservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="mailing-service")
public interface MailServiceProxy {
    @PostMapping("/send/to/{to}/token/{token}")
    void sendMail(@PathVariable String to, @PathVariable String token);
}
