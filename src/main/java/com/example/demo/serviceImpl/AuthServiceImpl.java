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
import com.example.demo.services.RoleServiceInterface;
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
	private RoleServiceInterface roleServiceInterface;

	@Autowired
	private CacheOperation cache;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Candidate candidate;

		if (!cache.isKeyExist(email, email)) {

			candidate = authRepository.findByEmail(email);
			cache.addInCache(email, email,candidate);

		} else {

			candidate = (Candidate) cache.getFromCache(email, email); // redisTemplate.opsForHash().get(email, email);

		}

		if (candidate == null) {

			throw new UsernameNotFoundException("candidate not found with Email: " + email);

		}

		return new org.springframework.security.core.userdetails.User(candidate.getEmail(), candidate.getPassword(), getAuthority(candidate));

	}
	// @Override
	// public UserEntity save(UserDto user) {
	// UserEntity newUser = new UserEntity();
	// newUser.setEmail(user.getEmail());
	// newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
	// newUser.setName(user.getName());
	// return authRepository.save(newUser);
	// }

	
	
	@Override
	public Boolean comparePassword(String password, String hashPassword) {

		return bcryptEncoder.matches(password, hashPassword);

	}

	@SuppressWarnings("unchecked")
	private ArrayList<SimpleGrantedAuthority> getAuthority(Candidate candidate) {

		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

		if (!cache.isKeyExist(candidate.getC_id() + "permission", candidate.getC_id() + "permission")) {

			ArrayList<SimpleGrantedAuthority> authorities1 = new ArrayList<>();
			ArrayList<String> permissions = roleServiceInterface.getPermissionByUserId(candidate.getC_id());
			permissions.forEach(permission -> {

				authorities1.add(new SimpleGrantedAuthority("ROLE_" + permission));

			});
			authorities = authorities1;
			cache.addInCache(candidate.getC_id() + "permission", candidate.getC_id() + "permission", authorities1);

		} else {

			authorities = (ArrayList<SimpleGrantedAuthority>) cache.getFromCache(candidate.getC_id() + "permission", candidate.getC_id() + "permission");

		}

		return authorities;

	}

}
