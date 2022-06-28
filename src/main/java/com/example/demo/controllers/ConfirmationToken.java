package com.example.demo.controllers;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entities.Candidate;

@Entity
@Table
public class ConfirmationToken {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tokenid;
	
	private String confirmationtoken;
	
	private Date createdDate;
	
	@OneToOne(targetEntity=Candidate.class,fetch=FetchType.EAGER)
	@JoinColumn(nullable=false,name="c_id")
	private Candidate candidate;
	
	public ConfirmationToken() {
		
	}
	
	public ConfirmationToken(Candidate candidate) {
		this.candidate=candidate;
		createdDate =new Date();
		confirmationtoken = UUID.randomUUID().toString();
		
	}

	public long getTokenid() {
		return tokenid;
	}

	public void setTokenid(long tokenid) {
		this.tokenid = tokenid;
	}

	public String getConfirmationtoken() {
		return confirmationtoken;
	}

	public void setConfirmationtoken(String confirmationtoken) {
		this.confirmationtoken = confirmationtoken;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}
	
}
