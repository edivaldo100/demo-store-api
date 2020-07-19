package com.edivaldo.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edivaldo.api.entities.Store;
import com.edivaldo.api.repositories.StoreRepository;
import com.edivaldo.api.services.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	private static final Logger log = LoggerFactory.getLogger(StoreServiceImpl.class);

	@Autowired
	private StoreRepository storeRepository;

	@Override
	public Optional<Store> findById(Long id) {
		log.info("Buscando uma Loja por Id {}", id);
		return Optional.ofNullable(storeRepository.findById(id));
	}

	@Override
	public Store persistir(Store store) {
		log.info("Persistindo Loja: {}", store);
		return this.storeRepository.save(store);
	}

	@Override
	public Optional<Store> findByName(String name) {
		log.info("Buscando uma Loja por Nome {}", name);
		return Optional.ofNullable(storeRepository.findByName(name));
	}

}
