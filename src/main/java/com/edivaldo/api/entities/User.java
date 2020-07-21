package com.edivaldo.api.entities;

import java.io.Serializable;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.edivaldo.api.enums.ProfileEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -5754246207015712518L;
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private ProfileEnum profile;
	private Store store;
	private Set<Ordered> ordered;
	
	public User() {
	}

	public User(String name, String email, String password, ProfileEnum profile, Store store) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.profile = profile;
		this.store = store;
	}

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.profile = ProfileEnum.ROLE_USUARIO;
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

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "profile", nullable = false)
	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<Ordered> getOrdered() {
		return ordered;
	}

	public void setOrdered(Set<Ordered> ordered) {
		this.ordered = ordered;
	}


}
