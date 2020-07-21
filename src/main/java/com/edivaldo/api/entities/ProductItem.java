package com.edivaldo.api.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "product_item")
//@Getter @Setter @NoArgsConstructor @ToString
public class ProductItem implements Serializable {

	private static final long serialVersionUID = -5754246207015712518L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Ordered ordered;
		
	@ManyToOne(fetch = FetchType.EAGER)
	private Product product;
	
	public ProductItem() {
	}

	public ProductItem(Long id, Ordered ordered, Product product) {
		this.ordered = ordered;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ordered getOrdered() {
		return ordered;
	}

	public void setOrdered(Ordered ordered) {
		this.ordered = ordered;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
