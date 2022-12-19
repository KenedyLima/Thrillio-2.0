package com.thrillio.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "authorities")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "email", nullable = false, unique = true, updatable = false, referencedColumnName = "email")
	private User user;
	
	@Column(length = 50, updatable = true, nullable = false)
	private String authority;

	public Authority() {
		super();
	}

	public Authority(User user, String authority) {
		super();
		this.user = user;
		this.authority = authority;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public long getId() {
		return id;
	}

	
}
