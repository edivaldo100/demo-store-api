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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edivaldo.api.dtos.StoreDto;
import com.edivaldo.api.response.Response;
import com.edivaldo.api.services.StoreService;

@RestController
@RequestMapping("/api/stores")
@CrossOrigin(origins = "*")
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	@PostMapping("/register")
	public ResponseEntity<Response<StoreDto>> register(@Valid @RequestBody StoreDto storeDto, BindingResult result) throws NoSuchAlgorithmException {
		return storeService.register(storeDto, result);
	}
	
	@GetMapping
	public ResponseEntity<Response<Page<StoreDto>>> listAll() throws NoSuchAlgorithmException {
		return storeService.listAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<StoreDto>> findById(@PathVariable("id") Long id) {

		return storeService.findByIdResp(id);
	}
	
	
	
}
