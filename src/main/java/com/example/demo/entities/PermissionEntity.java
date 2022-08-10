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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "permissions")
@Where(clause = "is_active = true")
@SQLDelete(sql="UPDATE permissions SET is_active=false WHERE id=?")
public class PermissionEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "action_name")
	private String actionName;

	@Column(name = "description")
	private String description;

	@Column(name = "method")
	private String method;
	
	@Column(name = "base_url")
	private String baseUrl;

	@Column(name = "path")
	private String path;

	@Column(name = "is_active")
	private Boolean isActive = true;

	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entity_id")
	private EntityEntity entityId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EntityEntity getEntityId() {
		return entityId;
	}
	public void setEntityId(EntityEntity entityId) {
		this.entityId = entityId;
	}

	public PermissionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PermissionEntity(Long id, String actionName, String description, String method, String baseUrl, String path,
			Boolean isActive, Date createdAt, Date updatedAt, EntityEntity entityId) {
		super();
		this.id = id;
		this.actionName = actionName;
		this.description = description;
		this.method = method;
		this.baseUrl = baseUrl;
		this.path = path;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.entityId = entityId;
	}
	
	

	


}







//@ManyToMany(fetch = FetchType.EAGER)
//@JoinTable(name = "roles_permission", joinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
//private Collection<RoleEntity> roles = new ArrayList<>();







