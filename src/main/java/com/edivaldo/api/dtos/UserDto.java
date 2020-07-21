package com.edivaldo.api.dtos;

import java.util.Optional;
import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserDto {
	
	private String name;
	private String email;
	private String password;
	private Optional<String> profile = Optional.empty();
	private Optional<String> store = Optional.empty();
	private Optional<Set<OrderedDto>> orderedDto = Optional.empty();
	
	public UserDto() {
	}
	public UserDto(String name, String email, String password, Optional<String> profile, Optional<String> store) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.profile = profile;
		this.store = store;
	}
	public UserDto(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}


	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotEmpty(message = "Email não pode ser vazio.")
	@Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
	@Email(message="Email inválido.")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotEmpty(message = "Senha não pode ser vazia.")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public Optional<String> getProfile() {
		return profile;
	}
	public void setProfile(Optional<String> profile) {
		this.profile = profile;
	}
	public Optional<String> getStore() {
		return store;
	}
	public void setStore(Optional<String> store) {
		this.store = store;
	}
	public Optional<Set<OrderedDto>> getOrderedDto() {
		return orderedDto;
	}
	public void setOrderedDto(Optional<Set<OrderedDto>> orderedDto) {
		this.orderedDto = orderedDto;
	}
	@Override
	public String toString() {
		return "UserDto [name=" + name + ", email=" + email + ", password=" + password + ", profile=" + profile
				+ ", store=" + store + ", orderedDto=" + orderedDto + "]";
	}
	

	
}
