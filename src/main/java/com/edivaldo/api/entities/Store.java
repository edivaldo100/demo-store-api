package com.edivaldo.api.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "store")
public class Store implements Serializable {

	private static final long serialVersionUID = 3960436649365666213L;
	
	private Long id;
	private String name;
	private Set<User> users;
	
	public Store() {
	}
	
	public Store(String name) {
		super();
		this.name = name;
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

	public void setName(String n) {
		this.name = n;
	}

	@OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", users=" + users + "]";
	}
	
}
