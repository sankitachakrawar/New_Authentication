package com.example.demo.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.dto.AssignRole;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.dto.ICandidateDto;
import com.example.demo.dto.IPermissionDto;
import com.example.demo.dto.RoleIdListDto;
import com.example.demo.entities.Candidate;
import com.example.demo.entities.Forgot_password_request;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserRoleEntity;
import com.example.demo.entities.UserRoleId;
import com.example.demo.exceptionHandling.*;
import com.example.demo.repositories.CandidateRepository;
import com.example.demo.repositories.ForgotPasswordRequestRepository;
import com.example.demo.repositories.RolePermissionRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRoleRepository;
import com.example.demo.services.CandidateService;
import com.example.demo.utils.JwtTokenUtil;
import com.example.demo.utils.PaginationUsingFromTo;

@Service
public class CandidateServiceImpl implements CandidateService {

	private PasswordEncoder passwordEncoder;

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private JwtTokenUtil jwttokenUtil;
	


	public CandidateServiceImpl(CandidateRepository candidateRepository) {
		this.candidateRepository = candidateRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public void addCandidate(CandidateDto candidateDto) {

		Candidate candidate=new Candidate();
		candidate.setName(candidateDto.getName());
		candidate.setAddress(candidateDto.getAddress());
		candidate.setEmail(candidateDto.getEmail());
		
//		String password=passwordEncoder.encode(candidate.getPassword());
//		candidate.setPassword(password);	
		candidate.setPassword(passwordEncoder.encode(candidateDto.getPassword()));
		candidate.setUsername(candidateDto.getUsername());
		candidateRepository.save(candidate);
		
		
		

	}
	
	

	public Candidate dtoToCandidate(CandidateDto candidateDto) {
		Candidate candidate2 = new Candidate();
		//candidate2.setId(candidateDto.getCandidateId());
		candidate2.setName(candidateDto.getName());
		candidate2.setEmail(candidateDto.getEmail());
		candidate2.setPassword(passwordEncoder.encode(candidateDto.getPassword()));
		candidate2.setAddress(candidateDto.getAddress());
		candidate2.setUsername(candidateDto.getUsername());		
		return candidate2;
	}

	public CandidateDto candidateToDto(Candidate candidate) {
		CandidateDto candidateDto = new CandidateDto();
		//candidateDto.setCandidateId(candidate.getId());
		candidateDto.setName(candidate.getName());
		candidateDto.setEmail(candidate.getEmail());
		candidateDto.setPassword(candidate.getPassword());
		candidateDto.setAddress(candidate.getAddress());
		candidateDto.setUsername(candidate.getUsername());
		return candidateDto;

	}

	@Override
	public Candidate updateCandidate(Candidate candidate1, Long id) {
		Candidate candidate = this.candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("candidate", "id", id));
		//candidate.setId(candidate1.getId());
		candidate.setName(candidate1.getName());
		candidate.setEmail(candidate1.getEmail());
		candidate.setPassword(candidate1.getPassword());
		candidate.setAddress(candidate1.getAddress());
		Candidate updatedCandidate = this.candidateRepository.save(candidate);
		// CandidateDto candidateDto2=this.candidateToDto(updatedCandidate);

		return updatedCandidate;

	}

	@Override
	public Candidate getCandidateById(Long id) {
		Candidate candidate = this.candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("candidate", "id", id));
		
		return candidate;
	}

//	@Override
//	public List<Candidate> getAllCandidates() {
//		
//		return this.candidateRepository.findAll();
//	}

	@Override
	public void deleteCandidate(Long id) {
		Candidate candidate = this.candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("candidate", "id", id));

		this.candidateRepository.deleteById(id);
	}

	
	@Override
	public Candidate findByEmail(String email) {
		Candidate candidate = candidateRepository.findByEmail(email);
		return candidate;

	}
	
	
	
	@Override
	public void changePassword(Long id, ChangePasswordDto userBody, HttpServletRequest request)
			throws ResourceNotFoundException {

		Candidate candidate = candidateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Candidate Not Found"));
		final String requestTokenHeader = request.getHeader("Authorization");
		String email = null;
		String jwtToken = null;
		//JsonObject jsonObj = null;
		jwtToken = requestTokenHeader.substring(7);
		email = jwttokenUtil.getEmailFromToken(jwtToken);
		System.out.println("objcet>>"+email);
		//jsonObj = JsonParser.parseString(email).getAsJsonObject();
		CandidateDto candidatedata = new CandidateDto();
		candidatedata.setEmail(email.toString());
		System.out.println("data>>>"+candidatedata);
		
		if (candidatedata.getEmail().equals(candidate.getEmail()) ) {
			System.out.println("dto>>"+candidatedata.getEmail()+"entity>>"+candidate.getEmail());
			if (!bcryptEncoder.matches(userBody.getNewPassword(), candidate.getPassword())) {
 
				if (bcryptEncoder.matches(userBody.getPassword(), candidate.getPassword())) {

					candidate.setPassword(bcryptEncoder.encode(userBody.getNewPassword()));
					if (userBody.getNewPassword().equals(userBody.getConfPassword())) {
						candidate.setPassword(bcryptEncoder.encode(userBody.getNewPassword()));
					} else {
						throw new ResourceNotFoundException("new password and confirm password must be same");
					}

				} else {

					throw new ResourceNotFoundException("Please enter old password correct");
				}

			} else {
				throw new ResourceNotFoundException("password must be differ from old password");
			}

		} else {
			throw new ResourceNotFoundException("Access Denied");
		}
		
		
	}
	
	
	@Autowired
	private ForgotPasswordRequestRepository forgotPasswordRequestRepository;
	
	  @Override
		public Boolean comparePassword(String password, String hashPassword) {

			return bcryptEncoder.matches(password, hashPassword);

		}

	@Override
	public Candidate findById(Long candidate_id) {
		return null;
		// TODO Auto-generated method stub
		
	}

	
//forgot password
	@Override
	public void forgotPasswordConfirm(String token, @Valid ForgotPasswordDto userBody, HttpServletRequest request) {
		DecodedJWT jwt = JWT.decode(token); 
		Date CurrentDate = new Date(System.currentTimeMillis());
		
		if (CurrentDate.before(jwt.getExpiresAt())) {

			if (userBody.getPassword().equals(userBody.getConfirmpassword())) {
				
				String email = null;
				String jwtToken = null; 
				jwtToken = userBody.getToken();
				email = jwttokenUtil.getEmailFromToken(jwtToken); 
				
				Candidate candidate = candidateRepository.getCandidateByEmail(email);

				candidate.setPassword(bcryptEncoder.encode(userBody.getPassword()));
				candidateRepository.save(candidate);

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

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Override
	public void addRoleToCandidate(AssignRole assignRole) {
		
		try {
			ArrayList<UserRoleEntity> roles=new ArrayList<>();
			Candidate email=candidateRepository.findByEmail(assignRole.getEmail());
			
			RoleEntity name=roleRepository.findByRoleNameContainingIgnoreCase(assignRole.getRoleName());
			
			UserRoleEntity userRoleEntity=new UserRoleEntity();
			
			UserRoleId userRoleId=new UserRoleId(email,name);
			
			userRoleEntity.setPk(userRoleId);
			roles.add(userRoleEntity);
			userRoleRepository.saveAll(roles);
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}

	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	
	@Override
	public List<IPermissionDto> getUserPermission(Long userId) throws IOException {

		ArrayList<RoleIdListDto> roleIds = userRoleRepository.findByPkUserId(userId, RoleIdListDto.class);
		ArrayList<Long> roles = new ArrayList<>();

		for (int i = 0; i < roleIds.size(); i++) {

			roles.add(roleIds.get(i).getPkRoleId());

		}

		return rolePermissionRepository.findByPkRoleIdIn(roles, IPermissionDto.class);

	}

	@Override
	public Page<ICandidateDto> getAllCandidates(String search, String from, String to) {
		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			
			return candidateRepository.findByOrderById(paging, ICandidateDto.class);
		} else {
			
			return candidateRepository.findByNameContainingIgnoreCaseOrderById(search, paging, ICandidateDto.class);
			
				
		}
	}
	

	
	  
}






