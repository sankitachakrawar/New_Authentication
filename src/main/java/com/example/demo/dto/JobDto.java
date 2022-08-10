package com.example.demo.dto;

import com.example.demo.entities.Recruiter;





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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCTC() {
		return CTC;
	}

	public void setCTC(String cTC) {
		CTC = cTC;
	}

	public Recruiter getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(Recruiter recruiterId) {
		this.recruiterId = recruiterId;
	}

	public JobDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobDto(String name, String location, boolean isActive, String description, String cTC,
			Recruiter recruiterId) {
		super();
		this.name = name;
		this.location = location;
		this.isActive = isActive;
		this.description = description;
		CTC = cTC;
		this.recruiterId = recruiterId;
	}
	
	
	
}
