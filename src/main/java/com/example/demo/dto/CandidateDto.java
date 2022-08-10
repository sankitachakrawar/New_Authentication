package com.example.demo.dto;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public CandidateDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CandidateDto(@NotEmpty @Size(min = 4, message = "Name must be min of 4 characters !!") String name,
			@Email(message = "Email address is not valid!!") @NotBlank(message = "email is mandatory") @Pattern(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$", message = "email must have @ and .") String email,
			@NotEmpty @Size(min = 3, max = 8, message = "Password must be min of 3 characters or max of 8 chracters") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password must be have 1 capital letter,one special charecter ,1numeric charecter") String password,
			@NotEmpty String address, String username) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.username = username;
	}

	
}
