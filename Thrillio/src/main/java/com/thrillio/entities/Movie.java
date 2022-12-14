package com.thrillio.entities;

import javax.persistence.Entity;

import com.thrillio.constants.KidFriendlyStatus;
import com.thrillio.constants.MovieGenre;

@Entity
public class Movie extends Bookmark {

	private String imageUrl;
	private int releaseYear;
	private MovieGenre genre;
	private double imdbRating;

	public Movie() {
	}

	public Movie(String title, String profileUrl, KidFriendlyStatus kidFriendlyStatus) {
		super(title, profileUrl, kidFriendlyStatus);
	}

	
	public Movie(String title, String profileUrl, KidFriendlyStatus kidFriendlyStatus, String imageUrl, int releaseYear,
			MovieGenre genre, double imdbRating) {
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

	public MovieGenre getGenre() {
		return genre;
	}

	public void setGenre(MovieGenre genre2) {
		this.genre = genre2;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

	@Override
	public boolean isKidFriendlyElegible() {
		if (genre.equals(MovieGenre.HORROR) || genre.equals(MovieGenre.THRILLERS)) {
			return false;
		}
		return true;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
