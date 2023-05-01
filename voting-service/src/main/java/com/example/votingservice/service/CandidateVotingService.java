package com.example.votingservice.service;


import com.example.votingservice.dto.request.CandidateVotingRequest;
import com.example.votingservice.dto.response.VotingResponse;

public interface CandidateVotingService {
    VotingResponse vote(CandidateVotingRequest request);
}
