package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.entities.Candidate;

public interface IRoleListDto extends Serializable {

	public Long getId();

	public String getRoleName();

	public String getDescription();

	public Boolean getIsActive();
	
	public Candidate getCandidate();

}
