package com.edivaldo.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.edivaldo.api.entities.ProductItem;


public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
	
	@Transactional(readOnly = true)
	ProductItem findById(Long id);
	
	@Transactional(readOnly = true)
	List<ProductItem> findAll();
}
