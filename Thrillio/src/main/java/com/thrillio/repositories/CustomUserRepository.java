package com.thrillio.repositories;

import org.springframework.data.jpa.repository.Query;

import com.thrillio.entities.User;

public interface CustomUserRepository {
	User findByEmail(String email);
}
