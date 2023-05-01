package com.example.votingservice.dto.request;

import lombok.Data;

@Data
public class NonCandidateVotingRequest {
    private long candidateId;
    private String voterEmail;
    private String token;
}
