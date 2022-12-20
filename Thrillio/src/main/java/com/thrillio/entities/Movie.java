package com.thrillio.entities;

import javax.persistence.Entity;

import com.thrillio.constants.KidFriendlyStatus;

@Entity
public class Movie extends Bookmark {

	private String imageUrl;
	private int releaseYear;
	private int[] genreIds;
	private double voteAverage;

	public Movie() {
	}

	public Movie(String title, String profileUrl, KidFriendlyStatus kidFriendlyStatus) {
		super(title, profileUrl, kidFriendlyStatus);
	}

	public Movie(String title, String profileUrl, KidFriendlyStatus kidFriendlyStatus, String imageUrl, int releaseYear,
			int[] genreIds, double voteAverage) {
		super(title, profileUrl, kidFriendlyStatus);
		this.imageUrl = imageUrl;
		this.releaseYear = releaseYear;
		this.genreIds = genreIds;
		this.voteAverage = voteAverage;

	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int[] getGenreIds() {
		return genreIds;
	}

	public void setGenreIds(int[] genreIds) {
		this.genreIds = genreIds;
	}

	public double getvoteAverage() {
		return voteAverage;
	}

	public void setvoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
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
