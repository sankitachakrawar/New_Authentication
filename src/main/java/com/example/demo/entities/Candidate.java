package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table
public class Candidate implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long c_id;
	
	@Column(name="candidate_name" , nullable=false , length=100)
	private String name;
	
	private String email;
	
	private String password;

	private String address;
	
	@Column(name = "is_active")
	private Boolean isActive = true;
	
	@SuppressWarnings("unused")
	private boolean isEnabled;

	private String username;
	
	
	public Long getC_id() {
		return c_id;
	}

	public void setC_id(Long c_id) {
		this.c_id = c_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	//@JoinTable(name = "candidate_jobs", joinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "c_id"), inverseJoinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"))
	private final Collection<Job> jobs = new ArrayList<>();
	
	//private List<Job> jobs;


	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Collection<Job> getJobs() {
		return jobs;
	}
	
	
}
