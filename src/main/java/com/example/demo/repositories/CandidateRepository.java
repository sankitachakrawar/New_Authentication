package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer>{

}
