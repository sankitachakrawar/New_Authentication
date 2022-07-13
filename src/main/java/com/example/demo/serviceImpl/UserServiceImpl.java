package com.example.demo.serviceImpl;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.example.demo.dto.IPermissionDto;
import com.example.demo.dto.IUserListDto;
import com.example.demo.dto.IUserRoleDetailDto;
import com.example.demo.dto.RoleIdListDto;
import com.example.demo.dto.UserDataDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRoleDto;
import com.example.demo.entities.RoleEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.entities.UserRoleEntity;
import com.example.demo.entities.UserRoleId;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.ForgotPasswordRequestRepository;
import com.example.demo.repositories.RolePermissionRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;
import com.example.demo.services.UserServiceInterface;
import com.example.demo.utils.JwtTokenUtil;
import com.example.demo.utils.PaginationUsingFromTo;

@Transactional
@Service("userServiceImpl")
public class UserServiceImpl extends JwtTokenUtil implements UserServiceInterface {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public UserServiceImpl() {

		// TODO Auto-generated constructor stub
	}

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	@Autowired
	private UserRepository userRepository;

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	/*
	 * @Autowired private AppSetting appSetting;
	 */

	/*
	 * @Autowired private JwtTokenUtil jwtTokenUtil;
	 */

	@Autowired
	ForgotPasswordRequestRepository forgotPasswordRequestRepository;

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
	public UserEntity findByEmail(String email) throws ResourceNotFoundException {

		UserEntity userData = userRepository.findByEmailAndIsActiveTrue(email)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		return userData;

	}

	@Override
	public void editUser(Long userId, UserDto userBody, Long adminId) throws ResourceNotFoundException {

		UserEntity userData = userRepository.findByIdAndIsActiveTrue(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		userData.setName(userBody.getName());
		userData.setEmail(userBody.getEmail());
		userData.setAddress(userBody.getAddress());
		
		
		userData.setDob(userBody.getDob());
		
		userRepository.save(userData);
		userRoleRepository.deleteByPkUserId(userId);


	}

	@Override
	public Page<IUserListDto> getAllUsers(String search, String from, String to) {

		Pageable paging = new PaginationUsingFromTo().getPagination(from, to);
		Page<IUserListDto> users;

		if ((search == "") || (search == null) || (search.length() == 0)) {

			users = userRepository.findByOrderByIdDesc(paging, IUserListDto.class);

		} else {

			users = userRepository
					.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrDesignationIdNameContainingIgnoreCaseOrderByIdDesc(
							StringUtils.trimLeadingWhitespace(search), StringUtils.trimLeadingWhitespace(search),
							StringUtils.trimLeadingWhitespace(search), paging, IUserListDto.class);

		}

		System.out.println(userRepository.findAll().size());
		return users;

	}

	@Override
	public void updateStatus(Long userId) throws ResourceNotFoundException {

		UserEntity userData = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		userData.setIsActive(!userData.getIsActive());
		return;

	}

	@Override
	public UserDataDto getUserRole(Long userId) throws ResourceNotFoundException 
	{
		UserEntity user = userRepository.findByIdAndIsActiveTrue(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
		UserDataDto userResp = new UserDataDto(userId, user.getName(), user.getEmail());
		
		userResp.setDob(user.getDob());

		ArrayList<RoleEntity> allRoles = roleRepository.findByIsActiveTrue();
		ArrayList<IUserRoleDetailDto> userDetail = userRoleRepository.findByPkUserIdAndPkUserIsActiveTrue(userId,
				IUserRoleDetailDto.class);
		ArrayList<UserRoleDto> userRoles = new ArrayList<>();

		for (int i = 0; i < allRoles.size(); i++) {

			UserRoleDto role = new UserRoleDto();
			role.setId(allRoles.get(i).getId());
			role.setName(allRoles.get(i).getRoleName());
			role.setIsSelected(false);

			for (IUserRoleDetailDto element : userDetail) {

				if (allRoles.get(i).getId() == element.getId()) {

					role.setIsSelected(true);
					break;

				}

			}

			userRoles.add(role);

		}

		userResp.setRoles(userRoles);
		return userResp;

	}

	@Override
	public void addUser(UserDto userDetail, Long userId) 
	{
		UserEntity newUser = new UserEntity();
		
		
			
		newUser.setAddress(userDetail.getAddress());
		
		
		newUser.setDob(userDetail.getDob());
		newUser.setEmail(userDetail.getEmail().toLowerCase());
		newUser.setName(userDetail.getName());
		
		
		UserEntity creatingUser = new UserEntity();
		creatingUser.setId(userId);
		newUser.setCreatedBy(creatingUser);
		newUser.setUpdatedBy(creatingUser);
		
		UserEntity addedUser = userRepository.save(newUser);
		UserRoleEntity userRole = new UserRoleEntity();
		UserEntity ue = new UserEntity();
		ue.setId(addedUser.getId());
		RoleEntity re = new RoleEntity();
		//re.setId(Long.parseLong((appSetting.getAllAppSetting().getSettings().get("defaultRoleId"))));
		// re.setId((long) (2));
		UserRoleId uri = new UserRoleId(ue, re);
		userRole.setPk(uri);
		userRoleRepository.save(userRole);

	}

	


	

	public static String getPlainString(String[] arrs) {

		String result = "";

		if (arrs.length > 0) {

			StringBuilder sb = new StringBuilder();

			for (String s : arrs) {

				sb.append(s).append(",");

			}

			result = sb.deleteCharAt(sb.length() - 1).toString();

		}

		return result;

	}

	/*
	 * @Override public List<UserEntity> getAllUsersCount() {
	 * 
	 * // TODO Auto-generated method stub return userRepository.findAll();
	 * 
	 * }
	 */
	
}
