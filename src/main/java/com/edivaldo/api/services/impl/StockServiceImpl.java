package com.edivaldo.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edivaldo.api.entities.Product;
import com.edivaldo.api.entities.Stock;
import com.edivaldo.api.entities.Store;
import com.edivaldo.api.repositories.StockRepository;
import com.edivaldo.api.repositories.StoreRepository;
import com.edivaldo.api.services.StockService;
import com.edivaldo.api.services.StoreService;

@Service
public class StockServiceImpl implements StockService {

	private static final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

	@Autowired
	private StockRepository stockRepository;

	@Override
	public Optional<Stock> findById(Long id) {
		log.info("Buscando Stock por Id {}", id);
		return Optional.ofNullable(stockRepository.findById(id));
	}

	@Override
	public Stock persistir(Stock stock) {
		log.info("Persistindo Stock: {}", stock);
		return this.stockRepository.save(stock);
	}

	@Override
	public Optional<Product> findByName(String name) {
		log.info("Buscando Stock por Nome do produto {}", name);
		return Optional.ofNullable(stockRepository.findByProductName(name));
	}

}
