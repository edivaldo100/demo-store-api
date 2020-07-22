package com.edivaldo.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.edivaldo.api.dtos.StoreDto;
import com.edivaldo.api.dtos.UserDto;
import com.edivaldo.api.response.Response;
import com.edivaldo.api.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Method for create User In the Store
	 * @param userDto
	 * @param result
	 * @return ResponseEntity<Response<UserDto>> 
	 */
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto, BindingResult result, UriComponentsBuilder ucBuilder){
		return userService.register(userDto, result, ucBuilder);
	}
	
	/**
	 * List all Users
	 * @return ResponseEntity<Response<Page<UserDto>>> 
	 */
	@GetMapping
	public ResponseEntity<Response<Page<UserDto>>> listAll() {
		return userService.listAll();
	}
	
	/**
	 * Method for update a User In the Store
	 * @param id
	 * @param userDto
	 * @param result
	 * @return ResponseEntity<Response<UserDto>>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<UserDto>> update(@PathVariable("id") Long id,
			@Valid @RequestBody UserDto userDto, BindingResult result) {
		return userService.update(id, userDto, result);
	}
	
	/**
	 * Method for update a User In the Store
	 * @param id
	 * @return ResponseEntity<Response<UserDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<UserDto>> findById(@PathVariable("id") Long id) {
		return userService.findByIdUser(id);
	}
}
