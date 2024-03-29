package com.thrillio.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Size(max = 15, min = 4, message = "Size should be between 4 - 15 characters")
	@NotEmpty(message = "Required Field")
	private String firstName;
	@Size(max = 15, min = 4, message = "Size should be between 4 - 15 characters")
	@NotEmpty(message = "Required Field")
	private String lastName;
	private Date birthDate;
	private int age;
	@Column(unique = true)
	private String email;
	
	private String password;
	@ColumnDefault(value = "1")
	private boolean enabled;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Bookmark.class)
	private Set<Bookmark> bookmarks = new HashSet<Bookmark>();
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, targetEntity = Authority.class)
	private Authority authority;

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public void setBookmaks(Set<Bookmark> bookmarks) {
		this.bookmarks = bookmarks;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
