package com.example.votingservice.service;

import com.example.registrationservice.models.Candidate;
import com.example.votingservice.dto.request.CandidateVotingRequest;
import com.example.votingservice.dto.response.VotingResponse;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateVotingServiceImpl implements CandidateVotingService{
    private final DataServiceProxyTwo dataServiceProxy;
    private final MailServiceProxyTwo mailServiceProxyTwo;
    public VotingResponse vote(CandidateVotingRequest request){
        Candidate candidate = dataServiceProxy.findCandidateById(request.getCandidateId());
        if (!(request.getToken().equals(candidate.getToken()))) throw new RuntimeException("Invalid or used token");
        long noOfVotes = candidate.getNoOfVotes();
        candidate.setNoOfVotes(++noOfVotes);
        candidate.setToken(hashToken(candidate.getToken()));
        dataServiceProxy.saveCandidate(candidate);
        mailServiceProxyTwo.sendMail(candidate.getEmail(), "Dear " + candidate.getFirstName() + " \nThank you for voting. Result will be uploaded soon");
        VotingResponse response = new VotingResponse();
        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("You have voted successfully");
        return response;
    }


    private String hashToken(String token){
        return BCrypt.hashpw(token, BCrypt.gensalt());
    }
}