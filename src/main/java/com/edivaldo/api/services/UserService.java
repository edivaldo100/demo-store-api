package com.edivaldo.api.services;

import java.util.Optional;

import com.edivaldo.api.entities.Store;
import com.edivaldo.api.entities.User;


public interface UserService {

	/**
	 * return User by id.
	 * 
	 * @param id
	 * @return Optional<User>
	 */
	Optional<User> findById(Long id);

	/**
	 * return User by email.
	 * 
	 * @param email
	 * @return Optional<User>
	 */
	Optional<User> findByEmail(String email);
	/**
	 * Create new User
	 * 
	 * @param User
	 * @return User
	 */
	User persistir(User user);

	
}
