package com.thrillio.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.thrillio.constants.KidFriendlyStatus;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Bookmark {

	@Id
	private long bookmarkId;
	private String title;

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
}
