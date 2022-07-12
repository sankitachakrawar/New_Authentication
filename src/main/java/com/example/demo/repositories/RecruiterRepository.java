package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Recruiter;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long>{

}
