package com.example.demo.entities;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import com.example.demo.dto.IRoleDetailDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@Where(clause = "is_active = true")
@SQLDelete(sql="UPDATE roles SET is_active=false WHERE id=?")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "description")
	private String description;

	@Column(name = "is_active")
	private Boolean isActive = true;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;
	
	
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.role", cascade = CascadeType.ALL)
		//private List<UserRoleEntity> userRole;
}

























//@OneToOne(fetch = FetchType.EAGER)   
//@JoinColumn(name = "c_id")
//private Candidate candidateId;




//@ManyToOne(fetch = FetchType.EAGER)
//@JoinColumn(name="candidate_id")
//private Candidate candidateId;


//public void setCandidateId(RoleEntity byId) {
//	// TODO Auto-generated method stub
//	
//}


//@ManyToMany(cascade = CascadeType.ALL)
//private List<PermissionEntity> permission;
