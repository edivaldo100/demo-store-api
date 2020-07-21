package com.edivaldo.api.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "ordered")
//@Getter @Setter @NoArgsConstructor @ToString
public class Ordered implements Serializable {

	private static final long serialVersionUID = -5754246207015712518L;
	
	private Long id;
	private User user;
	private Set<ProductItem> productItem;
	public Ordered() {
	}
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "ordered", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<ProductItem> getProductItem() {
		return productItem;
	}

	public void setProductItem(Set<ProductItem> productItem) {
		this.productItem = productItem;
	}

	@Override
	public String toString() {
		return "Ordered [id=" + id + ", user=" + user + ", productItem=" + productItem + "]";
	}
	
	
		
}
