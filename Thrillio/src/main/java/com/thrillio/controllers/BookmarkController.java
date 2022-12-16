package com.thrillio.controllers;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thrillio.entities.Movie;
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
	public Collection<Movie> getMovies() {
		return movieRepository.findAll();
	}

	@PostMapping("/movies")
	public Movie postMovie(@RequestBody Movie movie, Principal principal) {
		System.out.println(movie);
		boolean movieExists = movieRepository.existsById(movie.getId());
		if (!movieExists) {
			movie.setKidFriedlyElegible(movie.isKidFriendlyElegible());
			movieRepository.save(movie);
			System.out.println(userRepository);
			movie.setUser(userRepository.findByEmail(principal.getName()));
		}
		if(movieExists)
		System.out.println("Already bookmarked");
		
		return movie;
	}

	@DeleteMapping("/movies/{id}")
	public String deleteMovie(@PathVariable(name = "id") String movieId) {
		System.out.println("Deleting bookmark with id of" + movieId);
		String response = "";
		Optional<Movie> movie = movieRepository.findById(Long.parseLong(movieId));
		if (movie.isPresent()) {
			movieRepository.deleteById(Long.parseLong(movieId));
			response = "bookmark removed with id " + movieId + "was removed";
		}
		if (movie.isEmpty())
			response = "Bookmark not found";
		return response;
	}

}
