package com.example.votingservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="mailing-service")
public interface MailServiceProxyTwo {
    @PostMapping("/send/to/{to}/token/{token}")
    void sendMail(@PathVariable String to, @PathVariable String token);
}