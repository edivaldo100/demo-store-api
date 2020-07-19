package com.edivaldo.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.edivaldo.api.dtos.StoreDto;
import com.edivaldo.api.entities.Store;
import com.edivaldo.api.repositories.StoreRepository;
import com.edivaldo.api.response.Response;
import com.edivaldo.api.services.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	private static final Logger log = LoggerFactory.getLogger(StoreServiceImpl.class);

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private StoreService storeService;
	
	
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

	@Override
	public ResponseEntity<Response<StoreDto>> register(StoreDto storeDto, BindingResult result) {
		log.info("cadastro de loja {}", storeDto.toString());
		Response<StoreDto> response = new Response<>();
		
		validaData(storeDto, result);
		Store store = this.converterDtoToStore(storeDto);
		
		if(result.hasErrors()) {
			log.info("Erro na validação de Dados {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		this.storeService.persistir(store);
		StoreDto newStoreDto = this.converterStoreToDto(store);
		response.setData(newStoreDto);
		return ResponseEntity.ok(response);
	}
	
	private StoreDto converterStoreToDto(Store store) {
		return new StoreDto(store.getId(), store.getName());
	}

	private Store converterDtoToStore(StoreDto storeDto) {
		// TODO Auto-generated method stub
		return new Store(storeDto.getName());
	}

	/**
	 * data validation
	 * @param storeDto
	 * @param result
	 */
	
	private void validaData(StoreDto storeDto, BindingResult result) {
		//Optional<Store> findByName = storeService.findByName(storeDto.getName());
		//if(findByName.isPresent()) result.addError(new ObjectError("Loja", "Loja já cadastrada."));
		
		storeService.findByName(storeDto.getName())
		.ifPresent(store -> result.addError(new ObjectError("Loja", "Loja já cadastrada.")));
		
	}

}
