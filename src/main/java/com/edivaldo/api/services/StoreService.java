package com.edivaldo.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.edivaldo.api.dtos.StoreDto;
import com.edivaldo.api.entities.Store;
import com.edivaldo.api.response.Response;


public interface StoreService {

	/**
	 * return Store by id.
	 * 
	 * @param id
	 * @return Optional<Store>
	 */
	Optional<Store> findById(Long id);
	
	/**
	 * return Store by Name.
	 * 
	 * @param name
	 * @return Optional<Store>
	 */
	Optional<Store> findByName(String name);
	
	/**
	 * Create new Store
	 * 
	 * @param Store
	 * @return Store
	 */
	Store persistir(Store store);
	
	
	/**
	 * register new Store
	 * @param result 
	 * 
	 * @param StoreDto
	 * @return ResponseEntity<Response<StoreDto>>
	 */
	ResponseEntity<Response<StoreDto>> register(StoreDto storeDto, BindingResult result);

	
	/**
	 * list All Stores
	 * 
	 * @return ResponseEntity<Response<Page<StoreDto>>>
	 */
	ResponseEntity<Response<Page<StoreDto>>> listAll();
	
	
	/**
	 * return Store by id.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<StoreDto>>
	 */
	ResponseEntity<Response<StoreDto>> findByIdResp(Long id);
	
}
