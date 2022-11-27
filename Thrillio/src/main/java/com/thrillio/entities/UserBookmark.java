package com.thrillio.entities;

public class UserBookmark {

	public UserBookmark(User user, Bookmark bookmark) {
		super();
		this.user = user;
		this.bookmark = bookmark;
	}

	private User user;
	private Bookmark bookmark;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bookmark getBookmark() {
		return bookmark;
	}

	public void setBookmark(Bookmark bookmark) {
		this.bookmark = bookmark;
	}
}
