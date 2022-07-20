package com.example.demo.serviceImpl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Forgot_password_request;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.AuthRepository;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.repositories.ForgotPasswordRequestRepository;
import com.example.demo.services.AuthService;
import com.example.demo.utils.CacheOperation;
import com.example.demo.utils.JwtTokenUtil;

@Service
public class AuthServiceImpl implements AuthService{

	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private ForgotPasswordRequestRepository forgotPasswordRequestRepository;
	
	@Autowired
	private CacheOperation cache;
	
	@Autowired
	private AuthRepository authRepository;
	
	//forgot password
	@Override
	public void forgotPasswordConfirm(String token, @Valid ForgotPasswordDto userBody, HttpServletRequest request) {
		DecodedJWT jwt = JWT.decode(token); // give the current date
		Date CurrentDate = new Date(System.currentTimeMillis());

		// compare current date and expiredDate.
		if (CurrentDate.before(jwt.getExpiresAt())) {

			if (userBody.getPassword().equals(userBody.getConfirmpassword())) {

				// extract the email from token
				String username = null;
				String jwtToken = null; // get the token from payload
				jwtToken = userBody.getToken(); // get the email from token
				username = jwtTokenUtil.getEmailFromToken(jwtToken); // check if that email exist in database
				// grab the the user entity if email exist in db.
				Candidate candidate = candidateRepository.findByEmail(username);

				candidate.setPassword(bcryptEncoder.encode(userBody.getPassword()));

			} else {

				throw new ResourceNotFoundException("password and confirm password must be a same");

			}

		} else {

			Forgot_password_request forgot_password_request = forgotPasswordRequestRepository
					.getByTokenOrderByIdDesc(token).orElseThrow(() -> new ResourceNotFoundException("Invalid Request"));
			forgot_password_request.setIsActive(false);
			throw new ResourceNotFoundException("Reset the password time out");

		}
	}
	
	//login
	@Override
	public Candidate loginCandidate(String email, String password) throws Exception {
		Candidate candidate = candidateRepository.findByEmail(email);
		if (candidate == null) {
			throw new Exception("You entered incorrect Email.");
		} else {
			if (candidate.getEmail().equals(email) && candidate.getPassword().equals(password)) {
				return candidate;
			}
			throw new Exception("You entered incorrect password.");
		}

	}
	
	

}
