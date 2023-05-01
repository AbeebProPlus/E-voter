package com.example.registrationservice.service;

import com.example.registrationservice.dtos.requests.NonCandidateRegistrationRequest;
import com.example.registrationservice.dtos.response.RegistrationResponse;

public interface NonCandidateRegistrationService {
    RegistrationResponse saveNonCandidate(NonCandidateRegistrationRequest registrationRequest);
}