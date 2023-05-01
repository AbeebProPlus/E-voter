package com.example.votingservice.dto.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class VotingResponse {
    private HttpStatus httpStatus;
    private String message;
}
