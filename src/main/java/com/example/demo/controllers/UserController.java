package com.example.demo.controllers;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.EditUserRequestDto;
import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.dto.IUserListDto;
import com.example.demo.dto.SuccessResponseDto;
import com.example.demo.dto.UserDataDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServiceInterface;
import com.example.demo.dto.ListResponseDto;

@RestController
@RequestMapping("/user")
public class UserController {

	public UserController() {

		// TODO Auto-generated constructor stub
	}

	@Autowired
	private UserServiceInterface userServiceInterface;

	@Autowired
	private UserRepository userRepository;

	/*
	 * @Autowired private CvBuildServiceIntf cvBuildServiceIntf;
	 */
	@PreAuthorize("hasRole('getAllUser')")
	@GetMapping()
	public ResponseEntity<?> getAllUsers(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String size) {

		Page<IUserListDto> users = userServiceInterface.getAllUsers(search, pageNo, size);

		if (users.getTotalElements() != 0) {

			return new ResponseEntity<>(new SuccessResponseDto("Success", "success",
					new ListResponseDto(users.getContent(), users.getTotalElements())), HttpStatus.OK);

		}

		return new ResponseEntity<>(new ErrorResponseDto("Data Not Found", "dataNotFound"), HttpStatus.NOT_FOUND);

	}

	
	@PreAuthorize("hasRole('editUser')")
	@PutMapping("/{id}")
	public ResponseEntity<?> editUserAndRole(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody EditUserRequestDto userBody, HttpServletRequest request)
			throws ResourceNotFoundException {

		try {
			UserEntity userData = userRepository.findByIdAndIsActiveTrue(userId).orElseThrow(() -> new ResourceNotFoundException("first make it the active switch then do the editing"));
			
			userServiceInterface.editUser(userId, userBody,
					((UserDataDto) request.getAttribute("userData")).getUserId());
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "userNotFound"), HttpStatus.NOT_FOUND);

		}

	}

	

	@PreAuthorize("hasRole('changeUserStatus')")
	@PutMapping("/status/{id}")
	public ResponseEntity<?> editUserStatus(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {

		try {

			userServiceInterface.updateStatus(userId);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", null), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "userNotFound"), HttpStatus.NOT_FOUND);

		}

	}

	@PreAuthorize("hasRole('getUserById')")
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {

		try {

			UserDataDto userDetail = userServiceInterface.getUserRole(userId);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "success", userDetail), HttpStatus.OK);

		} catch (ResourceNotFoundException e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "userNotFound"), HttpStatus.NOT_FOUND);

		}

	}

	@PreAuthorize("hasRole('addUser')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserDto user, HttpServletRequest request)
			throws Exception, DataIntegrityViolationException {
		String email = user.getEmail();
		Optional<UserEntity> databaseName = userRepository.findByEmailContainingIgnoreCase(email);

		if (databaseName.isEmpty()) {
			userServiceInterface.addUser(user, ((UserDataDto) request.getAttribute("userData")).getUserId());
			return new ResponseEntity<>(new SuccessResponseDto("User Created", "userCreated", null),
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(new ErrorResponseDto("User Email Id Already Exist", "userEmailIdAlreadyExist"),
					HttpStatus.BAD_REQUEST);
		}
	}

	

	



	
	
}
