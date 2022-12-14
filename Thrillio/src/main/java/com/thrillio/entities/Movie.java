package com.thrillio.entities;

import javax.persistence.Entity;

import com.thrillio.constants.KidFriendlyStatus;
import com.thrillio.constants.MovieGenre;

@Entity
public class Movie extends Bookmark {

	private String imageUrl;
	private int releaseYear;
	private int[] genre;
	private double imdbRating;

	public Movie() {
	}

	public Movie(String title, String profileUrl, KidFriendlyStatus kidFriendlyStatus) {
		super(title, profileUrl, kidFriendlyStatus);
	}

	public Movie(String title, String profileUrl, KidFriendlyStatus kidFriendlyStatus, String imageUrl, int releaseYear,
			int[] genre, double imdbRating) {
		super(title, profileUrl, kidFriendlyStatus);
		this.imageUrl = imageUrl;
		this.releaseYear = releaseYear;
		this.genre = genre;
		this.imdbRating = imdbRating;

	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int[] getGenre() {
		return genre;
	}

	public void setGenre(int[] genre) {
		this.genre = genre;
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
