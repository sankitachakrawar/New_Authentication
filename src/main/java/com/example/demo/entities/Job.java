package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String location;
	@UpdateTimestamp
	private Date postTime;
	private boolean apply;
	
	
	//@ManyToOne
	//@JoinColumn(name="candidate_id")
	//private Candidate candidate;

	@ManyToMany(mappedBy="jobs")
	private final Collection<Candidate> candidate = new ArrayList<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Job(Long id, String title, String location, Date postTime, boolean apply) {
		super();
		this.id = id;
		this.title = title;
		this.location = location;
		this.postTime = postTime;
		this.apply = apply;
		
	}
public Collection<Candidate> getCandidate() {
	return candidate;
}
	
}
