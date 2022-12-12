package com.thrillio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thrillio.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
