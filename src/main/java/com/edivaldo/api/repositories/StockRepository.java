package com.edivaldo.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.edivaldo.api.entities.Product;
import com.edivaldo.api.entities.Stock;
import com.edivaldo.api.entities.User;


public interface StockRepository extends JpaRepository<Stock, Long> {
	
	@Transactional(readOnly = true)
	Stock findById(Long id);
	
	@Transactional(readOnly = true)
	Product findByProduct(Product product);
	
	@Transactional(readOnly = true)
	Product findByProductName(String productName);
}
