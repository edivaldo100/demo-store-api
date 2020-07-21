package com.edivaldo.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.edivaldo.api.entities.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Transactional(readOnly = true)
	Product findById(Long id);
	
	@Transactional(readOnly = true)
	Product findByName(String productName);
}
