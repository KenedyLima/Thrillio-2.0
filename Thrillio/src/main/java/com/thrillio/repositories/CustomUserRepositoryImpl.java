package com.thrillio.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.thrillio.entities.User;

public class CustomUserRepositoryImpl implements CustomUserRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public User findByEmail(String email) {
		Object singleResult = manager.createNativeQuery("Select * from users", User.class).getSingleResult();
		return (User) singleResult;

	}

}
