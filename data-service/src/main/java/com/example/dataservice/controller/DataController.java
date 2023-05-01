package com.example.dataservice.controller;

import com.example.dataservice.models.Candidate;
import com.example.dataservice.models.NonCandidate;
import com.example.dataservice.service.CandidateService;
import com.example.dataservice.service.NonCandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DataController {

    private final CandidateService candidateService;
    private final NonCandidateService nonCandidateService;

    @PostMapping("data/register-candidate")
    public void saveCandidate(@RequestBody Candidate candidate){
        candidateService.saveCandidate(candidate);
    }

    @PostMapping("data/register-non-candidate")
    public void saveNonCandidate(@RequestBody NonCandidate nonCandidate){
        nonCandidateService.saveNonCandidate(nonCandidate);
    }

    @PostMapping("data/validateCandidateEmail")
    public Candidate checkIfMailHasBeenUsedByCandidate(@RequestBody String email){
        return candidateService.checkIfMailHasBeenUsedByCandidate(email);
    }
    @PostMapping("data/validateNonCandidateEmail")
    public NonCandidate checkIfMailHasBeenUsedByNonCandidate(@RequestBody String email){
        return nonCandidateService.checkIfMailHasBeenUsedByNonCandidate(email);
    }
    @PostMapping("data/candidateId")
    public Candidate findCandidateById(@RequestBody Long id){
        return candidateService.findCandidateById(id);
    }
}
