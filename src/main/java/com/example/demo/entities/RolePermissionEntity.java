package com.example.demo.entities;

import java.sql.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_permission")
@AssociationOverrides({ @AssociationOverride(name = "pk.role", joinColumns = @JoinColumn(name = "role_id")), @AssociationOverride(name = "pk.permission", joinColumns = @JoinColumn(name = "permission_id")) })
public class RolePermissionEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RolePermissionId pk = new RolePermissionId();

	@Column(name = "is_active")
	private Boolean isActive = true;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;

	

}
