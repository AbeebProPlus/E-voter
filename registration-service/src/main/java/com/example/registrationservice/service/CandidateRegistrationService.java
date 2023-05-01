package com.example.registrationservice.service;

import com.example.registrationservice.dtos.requests.CandidateRegistrationRequest;
import com.example.registrationservice.dtos.response.RegistrationResponse;

public interface CandidateRegistrationService {
    RegistrationResponse registerCandidate(CandidateRegistrationRequest registrationRequest);
}
