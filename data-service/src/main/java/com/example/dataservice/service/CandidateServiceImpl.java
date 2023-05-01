package com.example.dataservice.service;

import com.example.dataservice.models.Candidate;
import com.example.dataservice.repos.CandidateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService{
    private final CandidateRepo candidateRepo;
    @Override
    public void saveCandidate(Candidate candidate) {
        candidateRepo.save(candidate);
    }

    @Override
    public Candidate checkIfMailHasBeenUsedByCandidate(String email) {
        return candidateRepo.findByEmail(email);
    }

    @Override
    public Candidate findCandidateById(Long id) {
        return candidateRepo.findById(id).get();
    }
}
