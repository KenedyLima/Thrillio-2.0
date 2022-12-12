package com.thrillio.repositories; 

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.thrillio.entities.User;

public class CustomUserRepositoryImpl implements CustomUserRepository{

	@PersistenceContext
	private EntityManager manager;

	@Override
	public User findByEmail(String email) {
		return (User) manager.createQuery("SELECT * FROM users WHERE email = '" + email + "'" );
	}

}
