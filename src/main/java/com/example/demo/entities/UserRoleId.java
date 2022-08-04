package com.example.demo.entities;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable

public class UserRoleId implements java.io.Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public UserRoleId() {

		super();

		// TODO Auto-generated constructor stub
	}

	public UserRoleId(Candidate user, RoleEntity role) {

		super();
		this.user = user;
		this.role = role;

	}

	private Candidate user;

	private RoleEntity role;

	@ManyToOne
	public Candidate getUser() {

		return user;

	}

	public void setUser(Candidate user) {

		this.user = user;

	}

	@ManyToOne
	public RoleEntity getRole() {

		return role;

	}

	public void setRole(RoleEntity role) {

		this.role = role;

	}

	@Override
	public int hashCode() {

		return Objects.hash(role, user);

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;

		}

		if ((obj == null) || (getClass() != obj.getClass())) {

			return false;

		}

		UserRoleId other = (UserRoleId) obj;
		return Objects.equals(role, other.role) && Objects.equals(user, other.user);

	}

}
