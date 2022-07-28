package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {

	@NotBlank(message = "password is Required*passwordRequired")
	@NotEmpty(message = "password is Required*passwordRequired")
	@NotNull(message = "password is Required*passwordRequired")
	private String password;

	@NotBlank(message = "newPassword is Required*newPasswordRequired")
	@NotEmpty(message = "newPassword is Required*newPasswordRequired")
	@NotNull(message = "newPassword is Required*newPasswordRequired")
	private String newPassword;
	
	@NotBlank(message = "confPassword is Required*confPasswordRequired")
	@NotEmpty(message = "confPassword is Required*confPasswordRequired")
	@NotNull(message = "confPassword is Required*confPasswordRequired")
	private String confPassword;
}
