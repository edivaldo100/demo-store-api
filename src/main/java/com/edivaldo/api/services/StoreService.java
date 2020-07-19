package com.edivaldo.api.services;

import java.util.Optional;

import com.edivaldo.api.entities.Store;


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
	
}
