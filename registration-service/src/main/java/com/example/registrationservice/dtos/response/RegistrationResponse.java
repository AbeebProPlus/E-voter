package com.example.registrationservice.dtos.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RegistrationResponse {
    private HttpStatus httpStatus;
    private String message;
}
