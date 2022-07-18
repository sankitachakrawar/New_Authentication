package com.example.demo.entities;

import java.sql.Date;


import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidate_role")
@AssociationOverrides({ @AssociationOverride(name = "pk.candidate", joinColumns = @JoinColumn(name = "id")), @AssociationOverride(name = "pk.role", joinColumns = @JoinColumn(name = "role_id")) })
public class UserRoleEntity implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UserRoleId pk = new UserRoleId();

	private Boolean isActive = true;

	private Date createdAt;

	private Date updatedAt;

	
}
