package com.edivaldo.api.services.impl;

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

import com.edivaldo.api.dtos.ProductDto;
import com.edivaldo.api.entities.Product;
import com.edivaldo.api.repositories.ProductRepository;
import com.edivaldo.api.response.Response;
import com.edivaldo.api.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ResponseEntity<Response<Page<ProductDto>>> listAll() {
		log.info("Lista de Produtos");
		Response<Page<ProductDto>> response = new Response<Page<ProductDto>>();
		try {
			PageRequest pageRequest = new PageRequest(0, 100, Direction.valueOf("ASC"), "id");
			Page<Product> findAll = this.productRepository.findAll(pageRequest);
			Page<ProductDto> pgDto = findAll.map(product -> this.converterProductToDto(product));
			response.setData(pgDto);
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			log.info("Erro Default");
			response.getErrors().add("Erro interno, tente mais tarde.");
			return ResponseEntity.badRequest().body(response);
		}
	}

	private ProductDto converterProductToDto(Product product) {
		return new ProductDto(product.getId(), product.getName(), product.getPrice());
	}

	@Override
	public ResponseEntity<Response<ProductDto>> create(ProductDto productDto, BindingResult result,
			UriComponentsBuilder ucBuilder) {
		log.info("cadastro de Produtos na loja {}", productDto.toString());
		Response<ProductDto> response = new Response<>();
		try {
			validaData(productDto, result);
			if (result.hasErrors()) {
				log.info("Erro na validação de Dados {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			Product product = this.converterDtoProduct(productDto);
			Product producSave = this.productRepository.save(product);
			ProductDto newDto = this.converterProductToDto(producSave);
			response.setData(newDto);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/products/{id}").buildAndExpand(newDto.getId()).toUri());
			return new ResponseEntity<Response<ProductDto>>(response, headers, HttpStatus.CREATED);
		} catch (Exception e) {
			log.info("Erro Default");
			response.getErrors().add("Erro interno, tente mais tarde.");
			return ResponseEntity.badRequest().body(response);
		}
	}

	private Product converterDtoProduct(ProductDto productDto) {
		return new Product(productDto.getId(), productDto.getName(), productDto.getPrice());
	}

	private void validaData(ProductDto productDto, BindingResult result) {
		Product findByName = this.productRepository.findByName(productDto.getName());
		if (findByName != null)
			result.addError(new ObjectError("product", "Produto já cadastrado."));
	}

	@Override
	public ResponseEntity<Response<ProductDto>> update(Long id, ProductDto productDto, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> delete(Long id) {
		log.info("deleta  Produtos na loja {}", id);
		Response<ProductDto> response = new Response<>();
		try {

			Product findById = this.productRepository.findById(id);
			if (findById == null) {
				log.info("Erro na validação de Dados {}");
				response.getErrors().add("Produto não encontrado.");
				return ResponseEntity.badRequest().body(response);
			}
			this.productRepository.delete(findById);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			log.info("Erro Default");
			response.getErrors().add("Erro interno, tente mais tarde.");
			return ResponseEntity.badRequest().body(response);
		}

	}

}
