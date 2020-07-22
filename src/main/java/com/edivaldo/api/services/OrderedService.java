package com.edivaldo.api.services;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.UriComponentsBuilder;

import com.edivaldo.api.dtos.OrderedDto;
import com.edivaldo.api.response.Response;


public interface OrderedService {

	/**
	 * List all Order service
	 * @return ResponseEntity<Response<Page<OrderedDto>>>
	 */
	ResponseEntity<Response<Page<OrderedDto>>> listAll();

	/**
	 * Create new Ordered
	 * @param orderedDto
	 * @param result
	 * @param ucBuilder 
	 * @return ResponseEntity<Response<OrderedDto>>
	 */
	ResponseEntity<Response<OrderedDto>> register(OrderedDto orderedDto, BindingResult result, UriComponentsBuilder ucBuilder);

	
	/**
	 * find By Order Number
	 * @param id
	 * @param result
	 * @return ResponseEntity<Response<OrderedDto>> 
	 */
	ResponseEntity<Response<OrderedDto>> findByOrderNumber(Long id, BindingResult result);

	/**
	 * find By Order Number
	 * @param orderNumber
	 * @return ResponseEntity<Response<OrderedDto>>
	 */
	ResponseEntity<Response<OrderedDto>> findByOrderNumber(Long orderNumber);

	
}
