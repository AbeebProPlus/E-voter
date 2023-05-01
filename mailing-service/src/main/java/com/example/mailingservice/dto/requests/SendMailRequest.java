package com.example.mailingservice.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class SendMailRequest {
    private String to;
    private String message;
}
