package com.example.demo.dto;

import java.util.Date;

import com.example.demo.entities.Candidate;

public class JobDto {

	private Long id;
	
	private String name;
	
	private String location;
	private Boolean apply=false;
	private Date postTime;
	
	private Candidate candidate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setApply(Boolean apply) {
		this.apply = apply;
	}
	public Boolean getApply() {
		return apply;
	}
	
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	public Candidate getCandidate() {
		return candidate;
	}
}
