package com.thrillio.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface BookmarkRepository<B> extends JpaRepository<B, Long> {
	
	Collection<B> findByUserId(Long userId);

	void deleteByBookmarkIdAndUserId(Long bookmarkId, Long userId);
}
