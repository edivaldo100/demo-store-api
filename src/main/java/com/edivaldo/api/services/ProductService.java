package com.edivaldo.api.services;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.edivaldo.api.dtos.ProductDto;
import com.edivaldo.api.response.Response;


public interface ProductService {
	
	ResponseEntity<Response<Page<ProductDto>>> listAll();

	ResponseEntity<Response<ProductDto>> create(ProductDto productDto, BindingResult result);

	ResponseEntity<Response<ProductDto>> update(Long id, ProductDto productDto, BindingResult result);

}
