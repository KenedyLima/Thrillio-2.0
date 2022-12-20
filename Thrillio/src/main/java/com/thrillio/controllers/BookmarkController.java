package com.thrillio.controllers;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thrillio.entities.Movie;
import com.thrillio.entities.User;
import com.thrillio.repositories.MovieRepository;
import com.thrillio.repositories.UserRepository;

@RestController
@RequestMapping("/bookmark-management")
public class BookmarkController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MovieRepository movieRepository;

	@GetMapping("/movies")
	public Collection<Movie> getMovies(Principal principal) {
		System.out.println("GET MOVIES");
		User user = userRepository.findByEmail("next");
		System.out.println("User in GetMovies: " + user.getFirstName());
		Collection<Movie> movies = movieRepository.findByUserIdOrderByBookmarkedDateDesc(user.getId());
		return movies;
	}

	@PostMapping("/movies")
	public Movie postMovie(@RequestBody Movie movie, Principal principal) {
		boolean movieExists = movieRepository.existsById(movie.getId());
		System.out.println("POST MOVIE");
		if (!movieExists) {
			User user = userRepository.findByEmail("next");
			movie.setKidFriedlyElegible(movie.isKidFriendlyElegible());
			movie.setUser(user);
			System.out.println("Movie Ids: " + movie.getGenreIds());
			movieRepository.save(movie);
		}
		if (movieExists)
			System.out.println("Already bookmarked");

		return movie;
	}

	@DeleteMapping("/movies/{id}")
	public String deleteMovie(@PathVariable(name = "id") String movieId, Principal principal) {
		User user = userRepository.findByEmail("next");
		String response = "";
		Optional<Movie> movie = movieRepository.findById(Long.parseLong(movieId));
		if (movie.isPresent()) {
			movieRepository.deleteByBookmarkIdAndUserId(Long.parseLong(movieId), user.getId());
			response = "bookmark removed with id " + movieId + "was removed";
		}
		if (movie.isEmpty())
			response = "Bookmark not found";
		return response;
	}

}
