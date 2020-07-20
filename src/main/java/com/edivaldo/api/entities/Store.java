package com.edivaldo.api.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store implements Serializable {

	private static final long serialVersionUID = 3960436649365666213L;
	
	private Long id;
	private String name;
	private List<User> users;
	
	public Store() {
	}
	
	public Store(String name) {
		super();
		this.name = name;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String n) {
		this.name = n;
	}


	@OneToMany(targetEntity=User.class, mappedBy="store",cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
	//@OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", users=" + users + "]";
	}
	
}
