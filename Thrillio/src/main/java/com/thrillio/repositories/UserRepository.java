
package com.thrillio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thrillio.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

	
}
