package com.edivaldo.api.services;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import com.edivaldo.api.dtos.ProductDto;
import com.edivaldo.api.response.Response;


public interface ProductService {
	/**
	 * list all products
	 * @return ResponseEntity<Response<Page<ProductDto>>>
	 */
	ResponseEntity<Response<Page<ProductDto>>> listAll();
	/**
	 * create a new product
	 * @param productDto
	 * @param result
	 * @param ucBuilder 
	 * @return ResponseEntity<Response<ProductDto>>
	 */
	ResponseEntity<Response<ProductDto>> create(ProductDto productDto, BindingResult result, UriComponentsBuilder ucBuilder);
	/**
	 * update Product
	 * @param id
	 * @param productDto
	 * @param result
	 * @return ResponseEntity<Response<ProductDto>>
	 */
	ResponseEntity<Response<ProductDto>> update(Long id, ProductDto productDto, BindingResult result);
	
	/**
	 * delete Product
	 * @param id
	 * @return ResponseEntity<?>
	 */
	ResponseEntity<?> delete(Long id);

}
