package com.thrillio.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.thrillio.constants.KidFriendlyStatus;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Bookmark {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long bookmarkId;
	private String title;
	private String profileUrl;
	private KidFriendlyStatus kidFriendlyStatus = KidFriendlyStatus.UNKNOWN;
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

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}
	
	public abstract boolean isKidFriendlyElegible();

	public KidFriendlyStatus getKidFriendlyStatus() {
		return kidFriendlyStatus;
	}

	public void setKidFriendlyStatus(KidFriendlyStatus kidFriendlyStatusDecision) {
		this.kidFriendlyStatus = kidFriendlyStatusDecision;
	}
}
