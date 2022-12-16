package com.thrillio.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.thrillio.constants.KidFriendlyStatus;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Bookmark {

	@Id
	private long bookmarkId;
	private String title;
	private boolean kidFriendlyElegible;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	public Bookmark(String title, String profileUrl, KidFriendlyStatus kidFriendlyStatus) {
		super();
		this.title = title;
	}

	public Bookmark() {
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

}
