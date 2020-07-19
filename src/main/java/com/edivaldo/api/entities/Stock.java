package com.edivaldo.api.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.edivaldo.api.enums.ProfileEnum;


@Entity
@Table(name = "stock")
public class Stock implements Serializable {

	private static final long serialVersionUID = -5754246207015712518L;
	
	private Long id;
	private Product product;
	private int quantity;
	
	
	public Stock() {
	}
	
	
	public Stock(Product product, int quantity) {
		super();
		this.product = product;
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
	
	//@OneToOne(fetch = FetchType.EAGER)
	@OneToOne(cascade=CascadeType.ALL)
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	} 
	
	
}
