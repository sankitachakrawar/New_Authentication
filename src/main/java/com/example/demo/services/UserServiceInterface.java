package com.example.demo.services;

import java.io.IOException;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.dto.IPermissionDto;
import com.example.demo.dto.IUserListDto;
import com.example.demo.dto.UserDataDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.ResourceNotFoundException;


public interface UserServiceInterface {

	List<IPermissionDto> getUserPermission(Long userId) throws IOException;

	UserEntity findByEmail(String email) throws ResourceNotFoundException;

	void editUser(Long userId, UserDto userBody, Long adminId) throws ResourceNotFoundException;

	//void editOnlySameUser(Long userId, UserDto userBody, Long adminId, HttpServletRequest request) throws ResourceNotFoundException;

	Page<IUserListDto> getAllUsers(String search, String from, String to);

	//List<UserEntity> getAllUsersCount();

	void updateStatus(Long userId) throws ResourceNotFoundException;

	UserDataDto getUserRole(Long userId) throws ResourceNotFoundException;

	void addUser(UserDto userDetail, Long userId);

	//void changePassword(Long userId, ChangePasswordDto userBody, HttpServletRequest request) throws ResourceNotFoundException;

	//void forgotPasswordConfirm(String token, ForgotPasswordDto userBody, HttpServletRequest request) throws ResourceNotFoundException;
	
	 
}
