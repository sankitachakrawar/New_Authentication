package com.example.demo.entities;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionId implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	@ManyToOne
	private RoleEntity role;
	@ManyToOne
	private PermissionEntity permission;

	
	
	@Override
	public int hashCode() {

		return Objects.hash(permission, role);

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;

		}

		if ((obj == null) || (getClass() != obj.getClass())) {

			return false;

		}

		RolePermissionId other = (RolePermissionId) obj;
		return Objects.equals(permission, other.permission) && Objects.equals(role, other.role);

	}

}
