package com.example.votingservice.service;

import com.example.registrationservice.models.Candidate;
import com.example.registrationservice.models.NonCandidate;
import com.example.votingservice.dto.request.NonCandidateVotingRequest;
import com.example.votingservice.dto.response.VotingResponse;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NonCandidateVotingServiceImpl implements NonCandidateVotingService{
    private final DataServiceProxyTwo dataServiceProxy;
    private final MailServiceProxyTwo mailServiceProxyTwo;
    @Override
    public VotingResponse vote(NonCandidateVotingRequest request) {
        Candidate candidate = dataServiceProxy.findCandidateById(request.getCandidateId());
        NonCandidate nonCandidate = dataServiceProxy.checkIfMailHasBeenUsedByNonCandidate(request.getVoterEmail());
        if (!(request.getToken().equals(nonCandidate.getToken()))) throw new RuntimeException("Invalid or used token");
        long noOfVotes = candidate.getNoOfVotes();
        candidate.setNoOfVotes(++noOfVotes);
        dataServiceProxy.saveCandidate(candidate);
        nonCandidate.setToken(hashToken(candidate.getToken()));
        dataServiceProxy.saveNonCandidate(nonCandidate);
        mailServiceProxyTwo.sendMail(nonCandidate.getEmail(), "Dear " + nonCandidate.getFirstName() + " \nThank you for voting. Results will be uploaded soon");
        VotingResponse response = new VotingResponse();
        response.setHttpStatus(HttpStatus.OK);
        response.setMessage("You have voted successfully");
        return response;
    }
    private String hashToken(String token){
        return BCrypt.hashpw(token, BCrypt.gensalt());
    }
}