package com.edivaldo.api.dtos;

public class ProductDto {
	private Long id;
	private String name;
	private Long price;
	public ProductDto() {
	}
	public ProductDto(Long id, String name, Long price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
}
