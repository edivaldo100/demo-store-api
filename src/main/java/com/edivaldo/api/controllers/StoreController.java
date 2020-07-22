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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.edivaldo.api.dtos.StoreDto;
import com.edivaldo.api.response.Response;
import com.edivaldo.api.services.StoreService;

@RestController
@RequestMapping("/api/stores")
@CrossOrigin(origins = "*")
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	/**
	 * Method for creating Store
	 * @param storeDto
	 * @param result
	 * @param ucBuilder
	 * @return ResponseEntity<Response<StoreDto>>
	 *
	 */
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody StoreDto storeDto, BindingResult result, UriComponentsBuilder ucBuilder) {
		return storeService.registerNewUser(storeDto, result, ucBuilder);
	}
	
	
	/**
	 * Method for listing of all Stores
	 * @return ResponseEntity<Response<Page<StoreDto>>>
	 */
	@GetMapping
	public ResponseEntity<Response<Page<StoreDto>>> listAll() {
		return storeService.listAll();
	}
	
	/**
	 * Method for find a Store By ID
	 * @param id
	 * @return ResponseEntity<Response<StoreDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<StoreDto>> findById(@PathVariable("id") Long id) {
		return storeService.findByIdResp(id);
	}
	
}
