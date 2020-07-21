package com.edivaldo.api.dtos;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.edivaldo.api.entities.Ordered;
import com.edivaldo.api.entities.Product;

public class ProductItemDTo {
    
	
	private Long id;	
	private ProductDto product;
	public ProductItemDTo() {
	}
	public ProductItemDTo(Long id, ProductDto product) {
		this.id = id;
		this.product = product;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "ProductItemDTo [id=" + id + ", product=" + product + "]";
	}
	
	
}
