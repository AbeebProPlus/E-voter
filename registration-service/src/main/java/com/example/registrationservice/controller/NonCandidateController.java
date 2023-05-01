package com.example.registrationservice.controller;


import com.example.registrationservice.dtos.requests.NonCandidateRegistrationRequest;
import com.example.registrationservice.service.NonCandidateRegistrationService;
import com.example.registrationservice.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequiredArgsConstructor
public class NonCandidateController {
    private final NonCandidateRegistrationService nonCandidateRegistrationService;
    @PostMapping("/register/non-candidate")
    public ResponseEntity<ApiResponse> registerNonCandidate(@RequestBody NonCandidateRegistrationRequest registrationRequest,
                                                            HttpServletRequest httpServletRequest){
        ApiResponse response = ApiResponse.builder()
                .data(nonCandidateRegistrationService.saveNonCandidate(registrationRequest))
                .isSuccessful(true)
                .statusCode(HttpStatus.CREATED)
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
