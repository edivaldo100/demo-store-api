package com.edivaldo.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.edivaldo.api.entities.Ordered;


public interface OrderedRepository extends JpaRepository<Ordered, Long> {
	
	@Transactional(readOnly = true)
	List<Ordered> findAll();
	
	@Transactional(readOnly = true)
	Ordered findById(Long id);
}
