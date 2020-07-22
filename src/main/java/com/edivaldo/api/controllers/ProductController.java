package com.edivaldo.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.edivaldo.api.dtos.ProductDto;
import com.edivaldo.api.response.Response;
import com.edivaldo.api.services.ProductService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	
	
	/**
	 * Method for creating product
	 * @param productDto
	 * @param result
	 * @param ucBuilder
	 * @return ResponseEntity<Response<ProductDto>>
	 */
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody ProductDto productDto, BindingResult result, UriComponentsBuilder ucBuilder) {
		return productService.create(productDto, result, ucBuilder);
	}
	
	/**
	 * Method for listing of all products
	 * @return ResponseEntity<Response<Page<ProductDto>>>
	 */
	@GetMapping
	public ResponseEntity<Response<Page<ProductDto>>> listAll() {
		return productService.listAll();
	}
	
	/**
	 * Method for creating product
	 * @param id
	 * @param productDto
	 * @param result
	 * @return ResponseEntity<Response<ProductDto>>
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<ProductDto>> update(@PathVariable("id") Long id,
			@Valid @RequestBody ProductDto productDto, BindingResult result) {
		return productService.update(id, productDto, result);
	}
	
	/**
	 * Method for delete product
	 * @param id
	 * @param productDto
	 * @param result
	 * @return ResponseEntity<?>
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delet(@PathVariable("id") Long id) {
		return productService.delete(id);
	}
}
