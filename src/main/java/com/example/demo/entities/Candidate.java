package com.example.demo.entities;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="candidate")
public class Candidate {

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
	
	
	private boolean isEnabled;

	private String username;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "candidate_jobs", joinColumns = @JoinColumn(name = "c_id", referencedColumnName = "c_id"), inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "id"))
	private Collection<Job> jobs = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	private Collection<Recruiter> recruiters=new ArrayList<>();
	
	
	/*
	 * public Long getC_id() { return c_id; }
	 * 
	 * public void setC_id(Long c_id) { this.c_id = c_id; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 * 
	 * public String getPassword() { return password; }
	 * 
	 * public void setPassword(String password) { this.password = password; }
	 * 
	 * public String getAddress() { return address; }
	 * 
	 * public void setAddress(String address) { this.address = address; }
	 * 
	 * public Boolean getIsActive() { return isActive; }
	 * 
	 * public void setIsActive(Boolean isActive) { this.isActive = isActive; }
	 * 
	 * public boolean isEnabled() { return isEnabled; }
	 * 
	 * public void setEnabled(boolean isEnabled) { this.isEnabled = isEnabled; }
	 * 
	 * public String getUsername() { return username; }
	 * 
	 * public void setUsername(String username) { this.username = username; }
	 * 
	 * public Collection<Job> getJobs() { return jobs; }
	 * 
	 * public void setJobs(Collection<Job> jobs) { this.jobs = jobs; }
	 * 
	 * public Candidate() { super(); // TODO Auto-generated constructor stub }
	 * 
	 * public Candidate(Long c_id, String name, String email, String password,
	 * String address, Boolean isActive, boolean isEnabled, String username,
	 * Collection<Job> jobs) { super(); this.c_id = c_id; this.name = name;
	 * this.email = email; this.password = password; this.address = address;
	 * this.isActive = isActive; this.isEnabled = isEnabled; this.username =
	 * username; this.jobs = jobs; }
	 * 
	 */

	
	
}
