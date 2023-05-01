package com.example.votingservice.service;


import com.example.registrationservice.models.Candidate;
import com.example.registrationservice.models.NonCandidate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="data-service")
public interface DataServiceProxyTwo {

    @PostMapping("data/candidateId")
    Candidate findCandidateById(@RequestBody Long id);

    @PostMapping("data/register-candidate")
    void saveCandidate(@RequestBody Candidate candidate);

    @PostMapping("data/validateCandidateEmail")
    Candidate checkIfMailHasBeenUsedByCandidate(@RequestBody String email);

    @PostMapping("data/validateNonCandidateEmail")
    NonCandidate checkIfMailHasBeenUsedByNonCandidate(@RequestBody String email);

    @PostMapping("data/register-non-candidate")
    void saveNonCandidate(@RequestBody NonCandidate nonCandidate);
}
