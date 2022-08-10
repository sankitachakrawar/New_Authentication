package com.example.demo.dto;

import java.util.Optional;


import com.example.demo.entities.Candidate;
import com.example.demo.entities.Job;


public class ApplyDto {
 public ApplyDto(Optional<Candidate> candidate2, Optional<Job> job2) {
		// TODO Auto-generated constructor stub
	}
private Candidate candidate;
 private Job job;
public Candidate getCandidate() {
	return candidate;
}
public void setCandidate(Candidate candidate) {
	this.candidate = candidate;
}
public Job getJob() {
	return job;
}
public void setJob(Job job) {
	this.job = job;
}
public ApplyDto() {
	super();
	// TODO Auto-generated constructor stub
}
public ApplyDto(Candidate candidate, Job job) {
	super();
	this.candidate = candidate;
	this.job = job;
}
 
 
 
 
}
