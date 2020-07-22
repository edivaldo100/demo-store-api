package com.edivaldo.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import com.edivaldo.api.dtos.UserDto;
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
	/**
	 * list all users
	 * @return ResponseEntity<Response<Page<UserDto>>>
	 */
	ResponseEntity<Response<Page<UserDto>>> listAll();
	/**
	 * create new user
	 * @param userDto
	 * @param result
	 * @param ucBuilder 
	 * @return ResponseEntity<Response<UserDto>>
	 */
	ResponseEntity<Response<UserDto>> register(UserDto userDto, BindingResult result, UriComponentsBuilder ucBuilder);
	/**
	 * update user By Id
	 * @param id
	 * @return ResponseEntity<Response<UserDto>>
	 */
	ResponseEntity<Response<UserDto>> updateById(Long id);
	/**
	 * update User
	 * @param id
	 * @param userDto
	 * @param result
	 * @return ResponseEntity<Response<UserDto>>
	 */
	ResponseEntity<Response<UserDto>> update(Long id, UserDto userDto, BindingResult result);
	/**
	 * find By Id User
	 * @param id
	 * @return ResponseEntity<Response<UserDto>>
	 */
	ResponseEntity<Response<UserDto>> findByIdUser(Long id);

	
}
