package com.thrillio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thrillio.entities.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
