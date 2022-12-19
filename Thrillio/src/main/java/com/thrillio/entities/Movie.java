package com.thrillio.entities;

import javax.persistence.Entity;

import com.thrillio.constants.KidFriendlyStatus;

@Entity
public class Movie extends Bookmark {

	private String imageUrl;
	private int releaseYear;
	private int[] genresIds;
	private double imdbRating;

	public Movie() {
	}

	public Movie(String title, String profileUrl, KidFriendlyStatus kidFriendlyStatus) {
		super(title, profileUrl, kidFriendlyStatus);
	}

	public Movie(String title, String profileUrl, KidFriendlyStatus kidFriendlyStatus, String imageUrl, int releaseYear,
			int[] genresIds, double imdbRating) {
		super(title, profileUrl, kidFriendlyStatus);
		this.imageUrl = imageUrl;
		this.releaseYear = releaseYear;
		this.genresIds = genresIds;
		this.imdbRating = imdbRating;

	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int[] getgenresIds() {
		return genresIds;
	}

	public void setgenresIds(int[] genresIds) {
		this.genresIds = genresIds;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

	//TODO:implement
	@Override
	public boolean isKidFriendlyElegible() {
		return true;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
