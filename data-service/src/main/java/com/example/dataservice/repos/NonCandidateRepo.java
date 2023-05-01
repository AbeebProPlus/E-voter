package com.example.dataservice.repos;

import com.example.dataservice.models.NonCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonCandidateRepo extends JpaRepository<NonCandidate, Long> {
    NonCandidate findByEmail(String email);
}