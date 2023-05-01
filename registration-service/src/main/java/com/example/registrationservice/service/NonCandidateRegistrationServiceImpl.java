package com.example.registrationservice.service;


import com.example.registrationservice.dtos.requests.NonCandidateRegistrationRequest;
import com.example.registrationservice.dtos.response.RegistrationResponse;
import com.example.registrationservice.exceptions.UserExistsException;
import com.example.registrationservice.models.Candidate;
import com.example.registrationservice.models.NonCandidate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class NonCandidateRegistrationServiceImpl implements NonCandidateRegistrationService{
    private final MailServiceProxy mailServiceProxy;
    private final DataServiceProxy dataServiceProxy;
    @Override
    public RegistrationResponse saveNonCandidate(NonCandidateRegistrationRequest registrationRequest) {
        Candidate candidate = checkIfEmailHasBeenUsedByCandidate(registrationRequest.getEmail());
        if (candidate != null) throw new UserExistsException("This email has been used");
        NonCandidate nonCandidate = validateMail(registrationRequest.getEmail());
        if (nonCandidate != null) throw new UserExistsException("This email has been used");
        validateMail(registrationRequest.getEmail());
        NonCandidate newNonCandidate = new NonCandidate();
        newNonCandidate.setFirstName(registrationRequest.getFirstName());
        newNonCandidate.setLastName(registrationRequest.getLastName());
        newNonCandidate.setEmail(registrationRequest.getEmail());
        String token = generateToken();
        sendMailtoCandidate(registrationRequest.getEmail(), "This is your voting token \n" + token);
        newNonCandidate.setToken(token);
        saveNonCandidate(newNonCandidate);
        return registrationResponse(newNonCandidate);
    }

    private RegistrationResponse registrationResponse(NonCandidate newNonCandidate) {
        RegistrationResponse response = new RegistrationResponse();
        response.setHttpStatus(HttpStatus.CREATED);
        response.setMessage("Voter with name " + newNonCandidate.getFirstName() + " created. " +
                "Please check your mail address for a token. You need the token to vote");

        return response;
    }

    private String generateToken(){
        StringBuilder token = new StringBuilder();
        SecureRandom numbers = new SecureRandom();
        for (int i = 0; i < 4; i++){
            int digit = numbers.nextInt(9);
            token.append(digit);
        }
        return token.toString();
    }
    private void sendMailtoCandidate(String to, String token){
        mailServiceProxy.sendMail(to, token);
    }

    private void saveNonCandidate(NonCandidate nonCandidate){
        dataServiceProxy.saveNonCandidate(nonCandidate);
    }

    private Candidate checkIfEmailHasBeenUsedByCandidate(String email){
        return dataServiceProxy.checkIfMailHasBeenUsedByCandidate(email);
    }
    private NonCandidate validateMail(String email){
        return dataServiceProxy.checkIfMailHasBeenUsedByNonCandidate(email);
    }
}
