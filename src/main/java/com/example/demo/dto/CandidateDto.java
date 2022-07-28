package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {

	private Long candidateId;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String address;
	
	private String username;

	
}
