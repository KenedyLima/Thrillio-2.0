package com.thrillio.controllers;

import java.util.Collection;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thrillio.entities.Book;
import com.thrillio.repositories.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BooksController {

	private BookRepository repository;
	
	@GetMapping
	public Collection<Book> getBooks() {
		return repository.findAll();
	}
	
	@PostMapping
	public Book postBook(@RequestBody Book book) {
		System.out.println("Posting");
		return repository.save(book);
	}
}
