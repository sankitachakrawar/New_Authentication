package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Candidate;
import com.example.demo.repositories.CandidateRepository;

import java.util.ArrayList;



@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Candidate candidate = candidateRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(candidate.getUsername(), candidate.getPassword(), new ArrayList<>());
      
       
    }
    
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
    	System.out.println("email>>"+email);
    	String abcd = email.trim().toString();
       Candidate candidate = candidateRepository.findByEmail(abcd);
       System.out.println("candidate>>"+candidate);
        return new org.springframework.security.core.userdetails.User(candidate.getEmail(), candidate.getPassword(), new ArrayList<>());
      
       
    }
    
}
