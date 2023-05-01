package com.example.votingservice.controller;

import com.example.votingservice.dto.request.CandidateVotingRequest;
import com.example.votingservice.dto.request.NonCandidateVotingRequest;
import com.example.votingservice.service.CandidateVotingService;
import com.example.votingservice.service.NonCandidateVotingService;
import com.example.votingservice.utils.ApiResponse;
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
public class VoteController {
    private final CandidateVotingService candidateVotingService;
    private final NonCandidateVotingService nonCandidateVotingService;
    @PostMapping("/vote/candidate")
    public ResponseEntity<ApiResponse> vote(@RequestBody CandidateVotingRequest request,
                                            HttpServletRequest httpServletRequest){
        ApiResponse response = ApiResponse.builder()
                .data(candidateVotingService.vote(request))
                .statusCode(HttpStatus.OK)
                .isSuccessful(true)
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/vote/non-candidate")
    public ResponseEntity<ApiResponse> voterByNonCandidate(@RequestBody NonCandidateVotingRequest request,
                                            HttpServletRequest httpServletRequest){
        ApiResponse response = ApiResponse.builder()
                .data(nonCandidateVotingService.vote(request))
                .statusCode(HttpStatus.OK)
                .isSuccessful(true)
                .timeStamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
