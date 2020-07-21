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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.edivaldo.api.dtos.OrderedDto;
import com.edivaldo.api.dtos.ProductDto;
import com.edivaldo.api.dtos.ProductItemDTo;
import com.edivaldo.api.entities.Ordered;
import com.edivaldo.api.entities.Product;
import com.edivaldo.api.entities.ProductItem;
import com.edivaldo.api.entities.User;
import com.edivaldo.api.repositories.OrderedRepository;
import com.edivaldo.api.repositories.UserRepository;
import com.edivaldo.api.response.Response;
import com.edivaldo.api.services.OrderedService;
import com.edivaldo.api.services.StoreService;

@Service
public class OrderedServiceImpl implements OrderedService {

	private static final Logger log = LoggerFactory.getLogger(OrderedServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StoreService storeService;
	
	@Autowired
	private OrderedService orderedService;
	@Autowired
	private OrderedRepository orderedRepository;
	@Override
	public Optional<Ordered> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User persistir(Ordered ordered) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<Page<OrderedDto>>> listAll() {
		log.info("Lista de Pedidos");
		Response<Page<OrderedDto>> response = new Response<Page<OrderedDto>>();
		PageRequest pageRequest = new PageRequest(0, 100, Direction.valueOf("ASC"), "id");
		Page<Ordered> findAll = this.orderedRepository.findAll(pageRequest);
		Page<OrderedDto> pgDto = findAll.map(ordered -> this.converterOrderedToDto(ordered));
		response.setData(pgDto);
		return ResponseEntity.ok(response);
	}

	private OrderedDto converterOrderedToDto(Ordered ordered) {
		OrderedDto orderedDto = new OrderedDto();
		
		Set<ProductItem> productItem = ordered.getProductItem();
		if(productItem != null) {
			Set<ProductItemDTo> productItemDto = converterProductItemDToToDto(productItem);
			orderedDto.setProductItemDTo(productItemDto);
		}
		Long orderNumber = ordered.getId();
		orderedDto.setOrderNumber(orderNumber);
		Long userId = ordered.getUser().getId();
		orderedDto.setUserId(userId);
		String userName = ordered.getUser().getName();
		orderedDto.setUserName(userName);
		
		
		return orderedDto;
	}
	
	private Set<ProductItemDTo> converterProductItemDToToDto(Set<ProductItem> productItem) {
		Set<ProductItemDTo> lista = new HashSet<>();
		productItem.forEach(action->{
			Product product2 = action.getProduct();
			ProductDto product = converterProductToDto(product2);
			Long id = action.getId();
			ProductItemDTo productItemDTo = new ProductItemDTo(id, product);
			lista.add(productItemDTo);
		});
		
		return lista;
	}

	private ProductDto converterProductToDto(Product product) {
		return new ProductDto(product.getId(), product.getName(), product.getPrice());
	}

	@Override
	public ResponseEntity<Response<OrderedDto>> register(OrderedDto orderedDto, BindingResult result) {
		log.info("cadastro de Order de pedido na loja {}", orderedDto.toString());
		Response<OrderedDto> response = new Response<>();
		
		validaData(orderedDto, result);
		if(result.hasErrors()) {
			log.info("Erro na validação de Dados {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		Ordered ordered = this.converteOrderedDtoToEntity(orderedDto);
		Ordered orderedSave = this.orderedRepository.saveAndFlush(ordered);
		OrderedDto newDto = this.converterOrderedToDto(orderedSave);
		response.setData(newDto);
		return ResponseEntity.ok(response);
	}

	private Ordered converteOrderedDtoToEntity(OrderedDto orderedDto) {
		Ordered ordered = new Ordered();
		ordered.setId(orderedDto.getOrderNumber());
		//Set<ProductItem> productItem;
		//ordered.setProductItem(productItem);
		User user = this.userRepository.findById(orderedDto.getUserId());
		ordered.setUser(user);
			
		return ordered;
	}

	private void validaData(OrderedDto orderedDto, BindingResult result) {
		Ordered findById = this.orderedRepository.findById(orderedDto.getOrderNumber());
		if(findById != null) result.addError(new ObjectError("order", "Número de Pedido já cadastrado."));
		
		 User findByIdUser = this.userRepository.findById(orderedDto.getUserId());
		if(findByIdUser == null) result.addError(new ObjectError("order", "Cliente com ID: "+orderedDto.getUserId()+" não cadastrado."));
		
		
	}

	@Override
	public ResponseEntity<Response<OrderedDto>> updateById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<OrderedDto>> update(Long id, OrderedDto orderedDto, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<OrderedDto>> findByOrderNumber(Long id, BindingResult result) {
		log.info("Consulta de Order de pedido na loja {}", id);
		Response<OrderedDto> response = new Response<>();
		
		Ordered ordered = this.orderedRepository.findById(id);
		if(ordered != null) result.addError(new ObjectError("order", "Número de Pedido já cadastrado."));
		
		if(result.hasErrors()) {
			log.info("Erro na validação de Dados {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		OrderedDto newDto = this.converterOrderedToDto(ordered);
		response.setData(newDto);
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<OrderedDto>> findByOrderNumber(Long orderNumber) {
		log.info("Consulta de Order de pedido na loja {}", orderNumber);
		Response<OrderedDto> response = new Response<>();
		
		Ordered ordered = this.orderedRepository.findById(orderNumber);
		if(ordered == null) {
			log.info("Erro na validação de Dados {}");
			response.getErrors().add("Número de Pedido Não cadastrado.");
			return ResponseEntity.badRequest().body(response);
		}
		OrderedDto newDto = this.converterOrderedToDto(ordered);
		response.setData(newDto);
		return ResponseEntity.ok(response);
	}
	
	

}
