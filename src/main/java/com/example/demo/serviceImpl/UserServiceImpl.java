package com.example.demo.serviceImpl;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.services.UserService;
import com.example.demo.entities.*;


@Transactional
@Service
public class UserServiceImpl implements UserService{

	
	
	/*
	 * private PasswordEncoder bcryptEncoder;
	 * 
	 * @Autowired private CandidateRepository candidateRepository;
	 * 
	 * @Autowired private ForgotPasswordRequestRepository
	 * forgotPasswordRequestRepository;
	 */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 return new User("admin","password",new ArrayList<>());
	}
	
	/*
	 * public void forgotPasswordConfirm(String token, @Valid ForgotPasswordDto
	 * userBody, HttpServletRequest request) {
	 * 
	 * DecodedJWT jwt = JWT.decode(token); Date CurrentDate = new
	 * Date(System.currentTimeMillis()); if (CurrentDate.before(jwt.getExpiresAt()))
	 * {
	 * 
	 * if (userBody.getPassword().equals(userBody.getConfirmpassword())) {
	 * 
	 * String username = null; String jwtToken = null;
	 * 
	 * jwtToken = userBody.getToken();
	 * 
	 * username = jwtTokenUtil.getEmailFromToken(jwtToken); Candidate userData =
	 * candidateRepository.findByEmailAndIsActiveTrue(username);//.orElseThrow(() ->
	 * new ResourceNotFoundException("Candidate Not Found"));
	 * userData.setPassword(bcryptEncoder.encode(userBody.getPassword()));
	 * 
	 * } else {
	 * 
	 * throw new
	 * ResourceNotFoundException("password and confirm password must be a same"); }
	 * }else {
	 * 
	 * Forgot_password_request forgot_password_request =
	 * forgotPasswordRequestRepository.getByTokenOrderByIdDesc(token).orElseThrow(()
	 * -> new ResourceNotFoundException("Invalid Request"));
	 * forgot_password_request.setIsActive(false); throw new
	 * ResourceNotFoundException("Reset the password time out");
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 */
	public void changePassword(Long userId, @Valid ChangePasswordDto userBody, HttpServletRequest request) {
	
		
		
	}

	@Override
	public void forgotPasswordConfirm(String token, @Valid ForgotPasswordDto userBody, HttpServletRequest request) {
				
	}

}
