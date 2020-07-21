package com.edivaldo.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.edivaldo.api.dtos.OrderedDto;
import com.edivaldo.api.entities.Ordered;
import com.edivaldo.api.entities.User;
import com.edivaldo.api.response.Response;


public interface OrderedService {

	/**
	 * return Ordered by id.
	 * 
	 * @param id
	 * @return Optional<Ordered>
	 */
	Optional<Ordered> findById(Long id);


	/**
	 * Create new Ordered
	 * 
	 * @param Ordered
	 * @return Ordered
	 */
	User persistir(Ordered ordered);

	ResponseEntity<Response<Page<OrderedDto>>> listAll();

	ResponseEntity<Response<OrderedDto>> register(OrderedDto orderedDto, BindingResult result);

	ResponseEntity<Response<OrderedDto>> updateById(Long id);

	ResponseEntity<Response<OrderedDto>> update(Long id, OrderedDto orderedDto, BindingResult result);

	
}
