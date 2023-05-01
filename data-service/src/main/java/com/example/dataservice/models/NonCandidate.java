package com.example.dataservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class NonCandidate extends Voter{
    @Id
    @GeneratedValue
    private Long id;
}