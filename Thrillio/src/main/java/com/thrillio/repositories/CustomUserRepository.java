package com.thrillio.repositories;

import com.thrillio.entities.User;

public interface CustomUserRepository {
	User findByEmail(String email);
}
