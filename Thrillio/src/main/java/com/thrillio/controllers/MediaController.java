package com.thrillio.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thrillio.entities.Movie;

@RestController
@RequestMapping("/media-management")
public class MediaController {

	private HttpClient client = HttpClient.newHttpClient();
	private final String TMDB_API_KEY = "c477b0a44244708a7d2aa95516f71908";

	@GetMapping("/movies/genre")
	public String getMovieGenres() throws IOException, InterruptedException {
		HttpRequest request = HttpRequest
				.newBuilder(URI.create("https://api.themoviedb.org/3/genre/movie/list?api_key=" + TMDB_API_KEY))
				.setHeader("Content-Type", "application/json").GET().build();
		String response = client.send(request, BodyHandlers.ofString()).body();
		return response;
	}
	
	@GetMapping("/movies")
	public String getMovies() throws IOException, InterruptedException {

		HttpRequest request = HttpRequest
				.newBuilder(URI.create("https://api.themoviedb.org/3/discover/movie?api_key=" + TMDB_API_KEY))
				.setHeader("Content-Type", "application/json").GET().build();
		
		String response = client.send(request, BodyHandlers.ofString()).body();
		return response;
	}
	
	@GetMapping("/books")
	public String getBooks() {
		
		return "";
	}
	
	@GetMapping("/weblinks")
	public String getWeblinks() {
		
		return "";
	}
}
