package com.edivaldo.api.services.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.edivaldo.api.dtos.UserDto;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<UserDto>> register(UserDto userDto, BindingResult result) {
		log.info("cadastro de Usuário na loja {}", userDto.toString());
		Response<UserDto> response = new Response<>();
		
		validaData(userDto, result);
		if(result.hasErrors()) {
			log.info("Erro na validação de Dados {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		User user = this.converterDtoToUser(userDto);
		User save = this.userRepository.save(user);
		UserDto newUserDto = this.converterUserToDto(save);
		response.setData(newUserDto);
		/*URI location = null;
		try {
			location = new URI("local");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (ResponseEntity<Response<UserDto>>) ResponseEntity.created(location);*/
		return ResponseEntity.ok(response);
	}

	private UserDto converterUserToDto(User saveUser) {
		UserDto userDto = new UserDto();
		userDto.setEmail(saveUser.getEmail());
		userDto.setName(saveUser.getName());
		userDto.setPassword(saveUser.getPassword());
		userDto.setProfile(Optional.of(saveUser.getProfile().toString()));
		userDto.setStore(Optional.of(saveUser.getStore().getName()));
		return userDto;
	}

	private User converterDtoToUser(UserDto userDto) {
		User user = new User();
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(PasswordUtils.gerarBCrypt(userDto.getPassword()));
		user.setProfile((!userDto.getProfile().isPresent())?ProfileEnum.ROLE_USUARIO:ProfileEnum.forName(userDto.getProfile().get()));
		Store store;
		if(userDto.getStore().isPresent()) store = storeService.findByName(userDto.getStore().get()).get();	else store = storeService.findById(1L).get();
		user.setStore(store);
		return user ;
	}

	/**
	 * data validation
	 * @param userDto
	 * @param result
	 */
	private void validaData(UserDto userDto, BindingResult result) {
		Optional<User> findByName = this.userRepository.findByName(userDto.getName());
		if(findByName.isPresent()) result.addError(new ObjectError("user", "Usuário já cadastrado."));
		
		userDto.getProfile().ifPresent(consumer -> {
			ProfileEnum forName = ProfileEnum.forName(consumer);
			if(forName == null)result.addError(new ObjectError("user", "Perfil de Usuário ( "+consumer+" ) não Existe."));
		});
		
		userDto.getStore().ifPresent(consumer -> {
			Optional<Store> opStore = storeService.findByName(userDto.getStore().get());
			if(!opStore.isPresent())result.addError(new ObjectError("user", "Loja ( "+consumer+" ) não Existe."));
		});
	}

}
