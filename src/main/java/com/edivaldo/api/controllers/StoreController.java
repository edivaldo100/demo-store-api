package com.edivaldo.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edivaldo.api.dtos.StoreDto;
import com.edivaldo.api.response.Response;
import com.edivaldo.api.services.StoreService;

@RestController
@RequestMapping("/api/register-store")
@CrossOrigin(origins = "*")
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	

	
	
	@PostMapping
	public ResponseEntity<Response<StoreDto>> register(@Valid @RequestBody StoreDto storeDto, BindingResult result) throws NoSuchAlgorithmException {
		return storeService.register(storeDto, result);
	}
	
}
