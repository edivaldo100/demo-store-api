package com.edivaldo.api.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

import com.edivaldo.api.dtos.OrderedDto;
import com.edivaldo.api.dtos.UserDto;
import com.edivaldo.api.entities.Ordered;
import com.edivaldo.api.entities.Store;
import com.edivaldo.api.entities.User;
import com.edivaldo.api.enums.ProfileEnum;
import com.edivaldo.api.repositories.UserRepository;
import com.edivaldo.api.response.Response;
import com.edivaldo.api.services.StoreService;
import com.edivaldo.api.services.UserService;
import com.edivaldo.api.utils.PasswordUtils;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StoreService storeService;

	@Autowired
	private OrderedServiceImpl orderedService;

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

	@Override
	public ResponseEntity<Response<Page<UserDto>>> listAll() {
		log.info("Lista de Users");
		Response<Page<UserDto>> response = new Response<Page<UserDto>>();
		try {
			PageRequest pageRequest = new PageRequest(0, 100, Direction.valueOf("ASC"), "id");
			Page<User> findAll = this.userRepository.findAll(pageRequest);
			Page<UserDto> pgDto = findAll.map(user -> this.converterUserToDto(user));
			response.setData(pgDto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.info("Erro Default");
			response.getErrors().add("Erro interno, tente mais tarde.");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<Response<UserDto>> register(UserDto userDto, BindingResult result,
			UriComponentsBuilder ucBuilder) {
		log.info("cadastro de Usuário na loja {}", userDto.toString());
		Response<UserDto> response = new Response<>();
		try {

			validaData(userDto, result);
			if (result.hasErrors()) {
				log.info("Erro na validação de Dados {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			User user = this.converterDtoToUser(userDto);
			User save = this.userRepository.save(user);
			UserDto newUserDto = this.converterUserToDto(save);
			response.setData(newUserDto);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/users/{id}").buildAndExpand(newUserDto.getId()).toUri());
			return new ResponseEntity<Response<UserDto>>(response, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			log.info("Erro Default");
			response.getErrors().add("Erro interno, tente mais tarde.");
			return ResponseEntity.badRequest().body(response);
		}
	}

	private UserDto converterUserToDto(User saveUser) {
		UserDto userDto = new UserDto();
		userDto.setId(saveUser.getId());
		userDto.setEmail(saveUser.getEmail());
		userDto.setName(saveUser.getName());
		userDto.setPassword(saveUser.getPassword());
		userDto.setProfile(Optional.of(saveUser.getProfile().toString()));
		userDto.setStore(Optional.of(saveUser.getStore().getName()));

		Set<Ordered> orderedList = saveUser.getOrdered();
		Set<OrderedDto> orderedDtoList = new HashSet<>();
		if (orderedList != null) {
			for (Ordered ordered : orderedList) {
				OrderedDto converterOrderedToDto = this.orderedService.converterOrderedToDto(ordered);
				orderedDtoList.add(converterOrderedToDto);
			}
			userDto.setOrderedDto(Optional.of(orderedDtoList));
		}
		return userDto;
	}

	private User converterDtoToUser(UserDto userDto) {
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(PasswordUtils.gerarBCrypt(userDto.getPassword()));
		user.setProfile((!userDto.getProfile().isPresent()) ? ProfileEnum.ROLE_USUARIO
				: ProfileEnum.forName(userDto.getProfile().get()));
		Store store;
		if (userDto.getStore().isPresent()) {
			store = storeService.findByName(userDto.getStore().get()).get();
		} else {
			store = storeService.findById(1L).get();
		}
		user.setStore(store);
		return user;
	}

	/**
	 * data validation
	 * 
	 * @param userDto
	 * @param result
	 */
	private void validaData(UserDto userDto, BindingResult result) {
		Optional<User> findByName = this.userRepository.findByName(userDto.getName());
		if (findByName.isPresent())
			result.addError(new ObjectError("user", "Usuário já cadastrado."));

		userDto.getProfile().ifPresent(consumer -> {
			ProfileEnum forName = ProfileEnum.forName(consumer);
			if (forName == null)
				result.addError(new ObjectError("user", "Perfil de Usuário ( " + consumer + " ) não Existe."));
		});

		userDto.getStore().ifPresent(consumer -> {
			Optional<Store> opStore = storeService.findByName(userDto.getStore().get());
			if (!opStore.isPresent())
				result.addError(new ObjectError("user", "Loja ( " + consumer + " ) não Existe."));
		});
	}

	private void validaDataUpdate(Long id, UserDto userDto, BindingResult result) {
		User findById = this.userRepository.findById(id);
		if (findById == null)
			result.addError(new ObjectError("user", "Usuário Não cadastrado."));

		userDto.getProfile().ifPresent(consumer -> {
			ProfileEnum forName = ProfileEnum.forName(consumer);
			if (forName == null)
				result.addError(new ObjectError("user", "Perfil de Usuário ( " + consumer + " ) não Existe."));
		});

		userDto.getStore().ifPresent(consumer -> {
			Optional<Store> opStore = storeService.findByName(userDto.getStore().get());
			if (!opStore.isPresent())
				result.addError(new ObjectError("user", "Loja ( " + consumer + " ) não Existe."));
		});
	}

	@Override
	public ResponseEntity<Response<UserDto>> updateById(Long id) {
		return null;
	}

	@Override
	public ResponseEntity<Response<UserDto>> update(Long id, UserDto userDto, BindingResult result) {
		log.info("update de Usuário na loja {}", userDto.toString());
		Response<UserDto> response = new Response<>();
		try {

			validaDataUpdate(id, userDto, result);
			if (result.hasErrors()) {
				log.info("Erro na validação de Dados {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			// User findById = this.userRepository.findById(id);

			User user = this.converterDtoToUser(userDto);
			user.setId(id);
			User save = this.userRepository.saveAndFlush(user);
			UserDto newUserDto = this.converterUserToDto(save);
			response.setData(newUserDto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.info("Erro Default");
			response.getErrors().add("Erro interno, tente mais tarde.");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<Response<UserDto>> findByIdUser(Long id) {
		log.info("Busca de Usuário na loja {}", id);
		Response<UserDto> response = new Response<>();
		try {

			User findById = this.userRepository.findById(id);
			if (findById == null) {
				response.getErrors().add("Id do Usuário não encontrado.");
				return ResponseEntity.badRequest().body(response);
			}
			UserDto newUserDto = this.converterUserToDto(findById);
			response.setData(newUserDto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.info("Erro Default");
			response.getErrors().add("Erro interno, tente mais tarde.");
			return ResponseEntity.badRequest().body(response);
		}
	}

}
