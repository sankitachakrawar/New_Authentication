package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Candidate;



public interface AuthRepository extends JpaRepository<Candidate, Long> {

	Candidate findByEmail(String email);

}