package com.yoda.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MemberRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "INT(8) UNSIGNED")
	private int id;
	
	@Column(nullable=false,length = 100, unique=false)
	private String email;
	
	@Column(nullable=false,length = 30, unique=false)
	private String role;

	
	public MemberRole() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberRole(int id, String email, String role) {
		super();
		this.id = id;
		this.email = email;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
