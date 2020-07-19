package com.edivaldo.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.edivaldo.api.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	@Transactional(readOnly = true)
	User findById(Long id);
	
	@Transactional(readOnly = true)
	User findByEmail(String email);
	
	@Transactional(readOnly = true)
	User findByName(String name);
}
