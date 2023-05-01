package com.example.votingservice.dto.request;

import lombok.Data;

@Data
public class CandidateVotingRequest {
    private Long candidateId;
    private String token;
}
