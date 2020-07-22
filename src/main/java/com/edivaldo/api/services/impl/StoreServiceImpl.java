package com.edivaldo.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.util.UriComponentsBuilder;

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
		try {

			validaData(storeDto, result);
			Store store = this.converterDtoToStore(storeDto);

			if (result.hasErrors()) {
				log.info("Erro na validação de Dados {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			this.storeService.persistir(store);
			StoreDto newStoreDto = this.converterStoreToDto(store);
			response.setData(newStoreDto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.info("Erro Default");
			response.getErrors().add("Erro interno, tente mais tarde.");
			return ResponseEntity.badRequest().body(response);
		}
	}

	private StoreDto converterStoreToDto(Store store) {
		return new StoreDto(store.getId(), store.getName());
	}

	private Store converterDtoToStore(StoreDto storeDto) {
		return new Store(storeDto.getName());
	}

	/**
	 * data validation
	 * 
	 * @param storeDto
	 * @param result
	 */
	private void validaData(StoreDto storeDto, BindingResult result) {
		// Optional<Store> findByName = storeService.findByName(storeDto.getName());
		// if(findByName.isPresent()) result.addError(new ObjectError("Loja", "Loja já
		// cadastrada."));
		storeService.findByName(storeDto.getName())
				.ifPresent(store -> result.addError(new ObjectError("Loja", "Loja já cadastrada.")));
	}

	@Override
	public ResponseEntity<Response<Page<StoreDto>>> listAll() {
		log.info("Lista de lojas");
		Response<Page<StoreDto>> response = new Response<Page<StoreDto>>();
		try {

			PageRequest pageRequest = new PageRequest(0, 100, Direction.valueOf("DESC"), "id");
			Page<Store> findAll = this.storeRepository.findAll(pageRequest);
			Page<StoreDto> pgStoreDto = findAll.map(store -> this.converterStoreToDto(store));
			response.setData(pgStoreDto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.info("Erro Default");
			response.getErrors().add("Erro interno, tente mais tarde.");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<Response<StoreDto>> findByIdResp(Long id) {
		log.info("Buscando Loja por ID: {}", id);
		Response<StoreDto> response = new Response<>();
		try {

			Optional<Store> store = storeService.findById(id);

			if (!store.isPresent()) {
				log.info("Loja não encontrada para o ID: {}", id);
				response.getErrors().add("Loja não encontrada para o ID " + id);
				return ResponseEntity.badRequest().body(response);
			}

			response.setData(this.converterStoreToDto(store.get()));
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.info("Erro Default");
			response.getErrors().add("Erro interno, tente mais tarde.");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<?> registerNewUser(StoreDto storeDto, BindingResult result, UriComponentsBuilder ucBuilder) {
		log.info("cadastro de loja {}", storeDto.toString());
		Response<StoreDto> response = new Response<>();
		try {

			validaData(storeDto, result);
			Store store = this.converterDtoToStore(storeDto);

			if (result.hasErrors()) {
				log.info("Erro na validação de Dados {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			this.storeService.persistir(store);
			StoreDto newStoreDto = this.converterStoreToDto(store);
			response.setData(newStoreDto);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/stores/{id}").buildAndExpand(newStoreDto.getId()).toUri());
			return new ResponseEntity<Response<StoreDto>>(response, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			log.info("Erro Default");
			response.getErrors().add("Erro interno, tente mais tarde.");
			return ResponseEntity.badRequest().body(response);
		}
	}

}
