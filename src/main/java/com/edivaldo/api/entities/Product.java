package com.edivaldo.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = -5754246207015712518L;
	
	private Long id;
	private String name;
	private Long price;
	//private Set<ProductItem> productItem;
	
	
	public Product() {
	}
	public Product(String name, Long price) {
		this.name = name;
		this.price = price;
	}
	
	public Product(Long id, String name, Long price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name", nullable = false)
	@JsonIgnore
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "price", nullable = false)
	@JsonIgnore
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	/*@OneToMany(mappedBy = "ordered", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	public Set<ProductItem> getProductItem() {
		return productItem;
	}
	public void setProductItem(Set<ProductItem> productItem) {
		this.productItem = productItem;
	}*/
	
	
}
