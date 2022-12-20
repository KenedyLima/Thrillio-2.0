package com.thrillio.repositories;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BookmarkRepository<B> extends JpaRepository<B, Long> {
	
	Collection<B> findByUserIdOrderByBookmarkedDateDesc(Long userId);

	@Transactional
	void deleteByBookmarkIdAndUserId(Long bookmarkId, Long userId);
}
