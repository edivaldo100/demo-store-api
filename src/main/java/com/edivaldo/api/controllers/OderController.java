package com.edivaldo.api.controllers;

import java.security.NoSuchAlgorithmException;

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

import com.edivaldo.api.dtos.OrderedDto;
import com.edivaldo.api.dtos.UserDto;
import com.edivaldo.api.response.Response;
import com.edivaldo.api.services.OrderedService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OderController {
	
	@Autowired
	private OrderedService OrderedService;
	
	@PostMapping("/register")
	public ResponseEntity<Response<OrderedDto>> register(@Valid @RequestBody UserDto userDto, BindingResult result) throws NoSuchAlgorithmException {
		return null;
		//return userService.register(userDto, result);
	}
	
	@GetMapping
	public ResponseEntity<Response<Page<OrderedDto>>> listAll() throws NoSuchAlgorithmException {
		return OrderedService.listAll();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<OrderedDto>> update(@PathVariable("id") Long id,
			@Valid @RequestBody UserDto userDto, BindingResult result) throws NoSuchAlgorithmException {
				return null;
		//return OrderedService.update(id, userDto, result);
	}
}
