package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.PermissionEntity;

import com.example.demo.repositories.CandidateRepository;


import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CandidateRepository candidateRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
    	Candidate candidate = candidateRepository.findByEmail(username);
        return new org.springframework.security.core.userdetails.User(candidate.getUsername(), candidate.getPassword(),getAuthority(candidate));
    }
    	
    
    

    
    
    
    
    
    @Autowired
    private RoleServiceInterface roleServiceInterface;
    
    private ArrayList<SimpleGrantedAuthority> getAuthority(Candidate candidate) {

		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

		//if (!cache.isKeyExist(candidate.getId() + "permission", candidate.getId() + "permission")) {

			ArrayList<SimpleGrantedAuthority> authorities1 = new ArrayList<>();
			ArrayList<String> permissions = roleServiceInterface.getPermissionByUserId(candidate.getId());
			//permissions.forEach(permission -> {

				authorities1.add(new SimpleGrantedAuthority("ROLE_" + permissions));

			//});
			authorities = authorities1;
			//cache.addInCache(candidate.getId() + "permission", candidate.getId() + "permission", authorities1);

		//} else {

			//authorities = (ArrayList<SimpleGrantedAuthority>) cache.getFromCache(candidate.getId() + "permission", candidate.getId() + "permission");

		//}

		return authorities;

	}



	
    	
      
       
    
    
    






	















	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
    	System.out.println("email>>"+email);
    	String abcd = email.trim().toString();
       Candidate candidate = candidateRepository.findByEmail(abcd);
       System.out.println("candidate>>"+candidate);
        return new org.springframework.security.core.userdetails.User(candidate.getEmail(), candidate.getPassword(), new ArrayList<>());
      
       
    }
    
}
