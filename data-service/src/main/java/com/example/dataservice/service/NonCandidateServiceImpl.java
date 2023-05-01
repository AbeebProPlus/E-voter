package com.example.dataservice.service;

import com.example.dataservice.models.NonCandidate;
import com.example.dataservice.repos.NonCandidateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NonCandidateServiceImpl implements NonCandidateService{
    private final NonCandidateRepo nonCandidateRepo;
    @Override
    public void saveNonCandidate(NonCandidate nonCandidate) {
        nonCandidateRepo.save(nonCandidate);
    }

    @Override
    public NonCandidate checkIfMailHasBeenUsedByNonCandidate(String email) {
        return nonCandidateRepo.findByEmail(email);
    }
}
