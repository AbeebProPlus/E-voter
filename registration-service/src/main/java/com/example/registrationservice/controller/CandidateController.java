package com.example.registrationservice.controller;

import com.example.registrationservice.dtos.requests.CandidateRegistrationRequest;
import com.example.registrationservice.service.CandidateRegistrationService;
import com.example.registrationservice.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateRegistrationService candidateRegistrationService;

    @PostMapping("register/candidate")
    public ResponseEntity<ApiResponse> registerCandidate(@RequestBody CandidateRegistrationRequest registrationRequest,
                                                         HttpServletRequest httpServletRequest){
        ApiResponse response = ApiResponse.builder()
                .data(candidateRegistrationService.registerCandidate(registrationRequest))
                .path(httpServletRequest.getRequestURI())
                .timeStamp(ZonedDateTime.now())
                .isSuccessful(true)
                .statusCode(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
