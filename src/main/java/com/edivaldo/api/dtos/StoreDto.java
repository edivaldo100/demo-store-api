package com.edivaldo.api.dtos;

import org.hibernate.validator.constraints.NotEmpty;

public class StoreDto {
	
	private Long id;
	private String name;
	
	
	
	public StoreDto() {
	}
	public StoreDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotEmpty(message = "Nome não pode ser vazio.")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "StoreDto [id=" + id + ", name=" + name + "]";
	}
	
}
