package com.example.dataservice.service;

import com.example.dataservice.models.NonCandidate;

public interface NonCandidateService {
    void saveNonCandidate(NonCandidate nonCandidate);
    NonCandidate checkIfMailHasBeenUsedByNonCandidate(String email);
}
