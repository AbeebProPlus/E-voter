package com.example.dataservice.service;

import com.example.dataservice.models.Candidate;

import java.util.Optional;

public interface CandidateService {
    void saveCandidate(Candidate candidate);
    Candidate checkIfMailHasBeenUsedByCandidate(String email);
    Candidate findCandidateById(Long id);
}
