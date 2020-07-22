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

import com.edivaldo.api.dtos.OrderedDto;
import com.edivaldo.api.response.Response;
import com.edivaldo.api.services.OrderedService;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OderController {
	
	@Autowired
	private OrderedService orderedService;
	
	
	/**
	 * Method for creating service order
	 * @param orderedDto
	 * @param result
	 * @param ucBuilder
	 * @return ResponseEntity<Response<OrderedDto>>
	 */
	@PostMapping("/register")
	public ResponseEntity<?> create(@Valid @RequestBody OrderedDto orderedDto, BindingResult result, UriComponentsBuilder ucBuilder){
		return orderedService.register(orderedDto, result, ucBuilder);
	}
	
	/**
	 * Method for listing of all service order
	 * @return ResponseEntity<Response<Page<OrderedDto>>>
	 */
	@GetMapping
	public ResponseEntity<Response<Page<OrderedDto>>> listAll() {
		return orderedService.listAll();
	}
	
	/**
	 * Method for find By Order Number
	 * @param orderNumber
	 * @return ResponseEntity<Response<OrderedDto>>
	 */
	@GetMapping(value = "/{orderNumber}")
	public ResponseEntity<Response<OrderedDto>> findByOrderNumber(@PathVariable("orderNumber") Long orderNumber) {
		return orderedService.findByOrderNumber(orderNumber);
	}
	
}
