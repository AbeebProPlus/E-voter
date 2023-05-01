package com.example.mailingservice.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class SendMailResponse {
    private HttpStatus httpStatus;
    private String message;
}
