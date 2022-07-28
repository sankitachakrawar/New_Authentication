package com.example.demo.dto;

import com.example.demo.entities.Recruiter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
	
	private String name;
	
	private String location;
	
	private boolean isActive;
	
	private String description;
	
	private String CTC;
	
	private Recruiter recruiterId;

	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
