package com.example.demo.dto;

import java.io.Serializable;
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
public class AuthRequestDto implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	@NotNull(message = "Email is Required*emailRequired")
	@NotEmpty(message = "Email is Required*emailRequired")
	@NotBlank(message = "Email is Required*emailRequired")
	private String email;

	@NotNull(message = "Password is Required*passwordRequired")
	@NotEmpty(message = "Password is Required*passwordRequired")
	@NotBlank(message = "Password is Required*passwordRequired")
	private String password;

	
	
	public static long getSerialversionuid() {

		return serialVersionUID;

	}

}