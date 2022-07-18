package com.example.demo.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordDto {

	@NotNull(message = "password is Required*passwordRequired")
	@NotEmpty(message = "password is Required*passwordRequired")
	@NotBlank(message = "password is Required*passwordRequired")
	private String password;

	@NotNull(message = "confirmpassword is Required*confirmpasswordRequired")
	@NotEmpty(message = "confirmpassword is Required*confirmpasswordRequired")
	@NotBlank(message = "confirmpassword is Required*confirmpasswordRequired")
	private String confirmpassword;

	@NotNull(message = "token is Required*tokenRequired")
	@NotEmpty(message = "token is Required*tokenRequired")
	@NotBlank(message = "token is Required*tokenRequired")
	private String token;


}
