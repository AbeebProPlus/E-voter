package com.example.registrationservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Candidate extends Voter {
    @Id
    @GeneratedValue
    private Long id;
    private long noOfVotes;
}
