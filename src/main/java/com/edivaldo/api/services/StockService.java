package com.edivaldo.api.services;

import java.util.Optional;

import com.edivaldo.api.entities.Product;
import com.edivaldo.api.entities.Stock;


public interface StockService {

	/**
	 * return Stock of Products by id.
	 * 
	 * @param id
	 * @return Optional<Stock>
	 */
	Optional<Stock> findById(Long id);
	
	/**
     * return Stock of Products by Name.
	 * 
	 * @param name
	 * @return Optional<Stock>
	 */
	Optional<Product> findByName(String name);
	
	/**
	 * Create new Stock
	 * 
	 * @param Stock
	 * @return Stock
	 */
	Stock persistir(Stock stock);
	
}
