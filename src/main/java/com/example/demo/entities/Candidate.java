package com.example.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

	
}





//@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//@JoinTable(
//        name = "candidate_roles",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "role_id")
//        )
//private Set<RoleEntity> roles = new HashSet<>();
//





//@ManyToMany(fetch = FetchType.LAZY)
//@JoinTable(name = "candidate_roles", joinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//private Collection<RoleEntity> roles = new ArrayList<>();