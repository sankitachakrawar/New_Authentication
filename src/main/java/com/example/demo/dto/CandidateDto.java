package com.example.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {

	//private Long candidateId;
	
	@NotEmpty
	@Size(min=4,message="Name must be min of 4 characters !!")
	private String name;
	
	@Email(message="Email address is not valid!!")
	@NotBlank(message="email is mandatory")
	//@Pattern(regexp = "^(.+)@(.+)$",message="email must have @ and .com")
	@Pattern(regexp="^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$",message="email must have @ and .")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=8,message="Password must be min of 3 characters or max of 8 chracters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message="Password must be have 1 capital letter,one special charecter ,1numeric charecter")
	private String password;
	
	@NotEmpty
	private String address;
	
	private String username;

	
}
