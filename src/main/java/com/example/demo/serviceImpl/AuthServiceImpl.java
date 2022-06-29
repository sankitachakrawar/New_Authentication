package com.example.demo.serviceImpl;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Candidate;
import com.example.demo.repositories.AuthRepository;
import com.example.demo.services.AuthInterface;
import com.example.demo.utils.CacheOperation;

@Service
public class AuthServiceImpl implements AuthInterface {

	public AuthServiceImpl() {

		// TODO Auto-generated constructor stub
	}

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private CacheOperation cache;

	
	
	
	@Override
	public Boolean comparePassword(String password, String hashPassword) {

		return bcryptEncoder.matches(password, hashPassword);

	}

	
}
