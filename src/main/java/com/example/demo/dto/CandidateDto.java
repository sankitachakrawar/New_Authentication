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

	private Long c_id;
	
	@NotEmpty
	@Size(min=4,message="Name must be min of 4 characters !!")
	private String name;
	
	@Email(message="Email address is not valid!!")
	@NotBlank(message="email is mandatory")
	@Pattern(regexp = "^(.+)@(.+)$")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=8,message="Password must be min of 3 characters or max of 8 chracters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message="Password must be min of 3 characters or max of 8 chracters")
	private String password;

	@NotEmpty
	private String address;
	
	private String username;

	/*
	 * public Long getC_id() { return c_id; }
	 * 
	 * public void setC_id(Long c_id) { this.c_id = c_id; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 * 
	 * public String getPassword() { return password; }
	 * 
	 * public void setPassword(String password) { this.password = password; }
	 * 
	 * public String getAddress() { return address; }
	 * 
	 * public void setAddress(String address) { this.address = address; } public
	 * void setUsername(String username) { this.username = username; } public String
	 * getUsername() { return username; }
	 */
	
}
