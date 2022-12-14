package com.thrillio.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.thrillio.entities.User;

public class CustomUserRepositoryImpl implements CustomUserRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public User findByEmail(String email) {
		User user = (User) manager.createNativeQuery("SELECT * FROM users WHERE email = '" + email + "'", User.class).getSingleResult();
		return user;
		
	}

}
