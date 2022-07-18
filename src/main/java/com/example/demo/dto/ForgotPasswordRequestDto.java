package com.example.demo.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordRequestDto {

	

	@NotNull(message = "Email is Required*emailRequired")
	@NotEmpty(message = "Email is Required*emailRequired")
	@NotBlank(message = "Email is Required*emailRequired")
	private String email;

	

}
