package com.example.demo.entities;

import java.util.ArrayList;


import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Table(name="candidate")
@Where(clause = "is_active = true")
@SQLDelete(sql="UPDATE candidate SET is_active=false WHERE id=?")
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	

	private String email;
	
	
	private String password;

	
	private String address;
	
	@Column(name = "is_active")
	private boolean isActive = true;
	
	private boolean isEnabled;

	private String username;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade = CascadeType.ALL)
	private List<UserRoleEntity> userRole;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserRoleEntity> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRoleEntity> userRole) {
		this.userRole = userRole;
	}

	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Candidate(Long id, String name, String email, String password, String address, boolean isActive,
			boolean isEnabled, String username, List<UserRoleEntity> userRole) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.isActive = isActive;
		this.isEnabled = isEnabled;
		this.username = username;
		this.userRole = userRole;
	}

	
	
}





//@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//@JoinTable(
//        name = "candidate_roles",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id")
//        )
//private Set<RoleEntity> roles = new HashSet<>();
//


//@ManyToMany(fetch = FetchType.EAGER)
//@JoinTable(name = "candidate_roles", joinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//private Collection<RoleEntity> roles = new ArrayList<>();




