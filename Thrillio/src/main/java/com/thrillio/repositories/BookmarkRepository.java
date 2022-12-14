package com.thrillio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thrillio.entities.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}
