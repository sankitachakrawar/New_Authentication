package com.example.demo.dto;

import java.util.Date;

import com.example.demo.entities.Candidate;

public class JobDto {

	private int j_id;
	
	private String title;
	
	private String location;
	private Boolean apply=false;
	private Date postTime;
	
	private Candidate candidate;

	public int getJ_id() {
		return j_id;
	}

	public void setJ_id(int j_id) {
		this.j_id = j_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
