package com.thrillio.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thrillio.constants.KidFriendlyStatus;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Bookmark {

	@Id
	private long bookmarkId;
	private String title;
	private boolean kidFriendlyElegible;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST, targetEntity = User.class)
	@JoinColumn(name = "user_id", updatable = false, referencedColumnName = "id", nullable = false)
	private User user;
	private Date bookmarkedDate;

	public Bookmark(String title, String profileUrl, KidFriendlyStatus kidFriendlyStatus) {
		super();
		this.title = title;
	}

	public Bookmark() {
		this.setBookmarkedDate(new Date());
	}

	public long getId() {
		return bookmarkId;
	}

	public void setId(long id) {
		this.bookmarkId = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public abstract boolean isKidFriendlyElegible();

	public void setKidFriedlyElegible(boolean kidFriedlyElegible) {
		this.kidFriendlyElegible = kidFriedlyElegible;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getBookmarkedDate() {
		return bookmarkedDate;
	}

	public void setBookmarkedDate(Date bookmarkedDate) {
		this.bookmarkedDate = bookmarkedDate;
	}

}
