package com.example.demo.entities;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class ApplyJob {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long job_id;
	private Long candidate_id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getJob_id() {
		return job_id;
	}
	public void setJob_id(Long job_id) {
		this.job_id = job_id;
	}
	public Long getCandidate_id() {
		return candidate_id;
	}
	public void setCandidate_id(Long candidate_id) {
		this.candidate_id = candidate_id;
	}
	public ApplyJob(Long id, Long job_id, Long candidate_id) {
		super();
		this.id = id;
		this.job_id = job_id;
		this.candidate_id = candidate_id;
	}
	public ApplyJob() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
