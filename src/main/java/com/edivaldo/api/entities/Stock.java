package com.edivaldo.api.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "stock")
public class Stock implements Serializable {

	private static final long serialVersionUID = -5754246207015712518L;
	
	private Long id;
	//private Set<Product> product;
	private int quantity;
	
	
	public Stock() {
	}
	
	public Stock(Long id, Set<Product> product, int quantity) {
		this.id = id;
		//this.product = product;
		this.quantity = quantity;
	}

	public Stock(Set<Product> product, int quantity) {
		//this.product = product;
		this.quantity = quantity;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	//@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	/*public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	} */
	
	
}
