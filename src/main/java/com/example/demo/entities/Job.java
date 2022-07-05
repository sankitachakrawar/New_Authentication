package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int j_id;
	
	private String title;
	
	private String location;
	@UpdateTimestamp
	private Date postTime;
	private boolean apply;
	
	
	@ManyToOne
	@JoinColumn(name="candidate_id")
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

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public Date getPostTime() {
		return postTime;
	}
	
	
	public boolean getApply() {
		return apply;
	}

	public void setApply(boolean apply) {
		this.apply = apply;
	}

	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
