package com.example.votingservice.service;

import com.example.votingservice.dto.request.CandidateVotingRequest;
import com.example.votingservice.dto.request.NonCandidateVotingRequest;
import com.example.votingservice.dto.response.VotingResponse;

public interface NonCandidateVotingService {
    VotingResponse vote(NonCandidateVotingRequest request);
}
