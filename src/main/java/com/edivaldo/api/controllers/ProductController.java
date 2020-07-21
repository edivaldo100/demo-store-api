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
import com.edivaldo.api.dtos.ProductDto;
import com.edivaldo.api.dtos.UserDto;
import com.edivaldo.api.response.Response;
import com.edivaldo.api.services.OrderedService;
import com.edivaldo.api.services.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/register")
	public ResponseEntity<Response<ProductDto>> register(@Valid @RequestBody ProductDto productDto, BindingResult result) throws NoSuchAlgorithmException {
		return productService.create(productDto, result);
	}
	
	@GetMapping
	public ResponseEntity<Response<Page<ProductDto>>> listAll() throws NoSuchAlgorithmException {
		return productService.listAll();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<ProductDto>> update(@PathVariable("id") Long id,
			@Valid @RequestBody ProductDto productDto, BindingResult result) throws NoSuchAlgorithmException {
		return productService.update(id, productDto, result);
	}
}
