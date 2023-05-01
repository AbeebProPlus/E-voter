package com.example.registrationservice.service;

import com.example.registrationservice.exceptions.UserExistsException;
import com.example.registrationservice.models.Candidate;
import com.example.registrationservice.dtos.requests.CandidateRegistrationRequest;
import com.example.registrationservice.dtos.response.RegistrationResponse;
import com.example.registrationservice.models.NonCandidate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class CandidateRegistrationServiceImpl implements CandidateRegistrationService{
    private final MailServiceProxy mailServiceProxy;

    private final DataServiceProxy dataServiceProxy;
    @Override
    public RegistrationResponse registerCandidate(CandidateRegistrationRequest registrationRequest) {
        validateRequest(registrationRequest);
        Candidate newCandidate = new Candidate();
        newCandidate.setFirstName(registrationRequest.getFirstName());
        newCandidate.setLastName(registrationRequest.getLastName());
        newCandidate.setEmail(registrationRequest.getEmail());
        String token = generateToken();
        sendMailtoCandidate(registrationRequest.getEmail(), "This is your voting token \n" + token);
        newCandidate.setToken(token);
        saveCandidate(newCandidate);
        return registrationResponse(newCandidate);
    }

    private void validateRequest(CandidateRegistrationRequest registrationRequest) {
        NonCandidate nonCandidate = checkIfEmailHasBeenUsedByNonCandidate(registrationRequest.getEmail());
        if (nonCandidate != null) throw new UserExistsException("This email has been used");
        Candidate candidate = validateMail(registrationRequest.getEmail());
        if (candidate != null) throw new UserExistsException("This email has been used");
    }

    private RegistrationResponse registrationResponse(Candidate newCandidate) {
        RegistrationResponse response = new RegistrationResponse();
        response.setHttpStatus(HttpStatus.CREATED);
        response.setMessage("Candidate with name " + newCandidate.getFirstName() + " created. " +
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

    private void saveCandidate(Candidate candidate){
        dataServiceProxy.saveCandidate(candidate);
    }

    private NonCandidate checkIfEmailHasBeenUsedByNonCandidate(String email){
        return dataServiceProxy.checkIfMailHasBeenUsedByNonCandidate(email);
    }
    private Candidate validateMail(String email){
        return dataServiceProxy.checkIfMailHasBeenUsedByCandidate(email);
    }
}