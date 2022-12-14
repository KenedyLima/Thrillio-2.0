package com.thrillio.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

@Entity(name = "authorities")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"email", "authority"}))
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
	private User user;	
	@Column(length = 50)
	private String authority;
	
	public Authority() {

	}
	
	public Authority(User user) {
		this.user = user;
		this.authority = "ROLE_USER";
	}
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
}
