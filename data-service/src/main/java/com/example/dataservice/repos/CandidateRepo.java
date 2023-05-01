package com.example.dataservice.repos;

import com.example.dataservice.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateRepo extends JpaRepository<Candidate, Long> {
    Candidate findByEmail(String email);
    Optional<Candidate> findById(Long id);
}