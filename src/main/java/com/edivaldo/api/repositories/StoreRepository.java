package com.edivaldo.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.edivaldo.api.entities.Store;


public interface StoreRepository extends JpaRepository<Store, Long> {
	
	@Transactional(readOnly = true)
	Store findById(Long id);

	@Transactional(readOnly = true)
	Store findByName(String name);
	
	@Transactional(readOnly = true)
	List<Store> findAll();
}
