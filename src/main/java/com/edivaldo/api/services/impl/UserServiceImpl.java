package com.edivaldo.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edivaldo.api.entities.User;
import com.edivaldo.api.repositories.UserRepository;
import com.edivaldo.api.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findById(Long id) {
		log.info("Buscando Usuario por Id {}", id);
		return Optional.ofNullable(userRepository.findById(id));
	}

	@Override
	public User persistir(User user) {
		log.info("Persistindo Usuario: {}", user);
		return this.userRepository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		log.info("Buscando Usuario por Email {}", email);
		return Optional.ofNullable(userRepository.findByEmail(email));
	}

}
