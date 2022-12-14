package com.thrillio.controllers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thrillio.entities.Movie;

@RestController
@RequestMapping("/content")
public class ContentController {

	private final String TMDB_API_KEY = "";
	private final HttpClient CLIENT = HttpClient.newHttpClient();

	@GetMapping("/movies")
	public Set<Movie> getMovies() {

		HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.themoviedb.org/3/discover/movie?api_key=" + TMDB_API_KEY))
				.setHeader("Content-Type", "application/json").build();
		Set<Movie> movies = null;

		return movies;
	}
}
