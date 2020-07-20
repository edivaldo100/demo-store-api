package com.edivaldo.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.edivaldo.api.dtos.UserDto;
import com.edivaldo.api.entities.Store;
import com.edivaldo.api.entities.User;
import com.edivaldo.api.response.Response;


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

	ResponseEntity<Response<Page<UserDto>>> listAll();

	ResponseEntity<Response<UserDto>> register(UserDto userDto, BindingResult result);

	
}
