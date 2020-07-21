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
		Set<ProductItemDTo> productItemDto = converterProductItemDToToDto(productItem);
		
		Long orderNumber = ordered.getId();
		orderedDto.setOrderNumber(orderNumber);
		Long userId = ordered.getUser().getId();
		orderedDto.setUserId(userId);
		String userName = ordered.getUser().getName();
		orderedDto.setUserName(userName);
		orderedDto.setProductItemDTo(productItemDto);
		
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
		// TODO Auto-generated method stub
		return null;
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
	
	

}
