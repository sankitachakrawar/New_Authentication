package com.example.demo.services;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.ForgotPasswordDto;
import com.example.demo.dto.IPermissionDto;
import com.example.demo.entities.Candidate;
import com.example.demo.exceptions.UsernameNotFoundException;

public interface UserService  {

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	public void forgotPasswordConfirm(String token, @Valid ForgotPasswordDto userBody, HttpServletRequest request);
	public void changePassword(Long userId, @Valid ChangePasswordDto userBody, HttpServletRequest request);
	public Candidate findByEmail(String email);
	public List<IPermissionDto> getUserPermission(Long c_id);
}
