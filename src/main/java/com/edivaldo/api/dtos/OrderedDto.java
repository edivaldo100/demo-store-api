package com.edivaldo.api.dtos;

import java.util.List;
import java.util.Set;

import com.edivaldo.api.entities.ProductItem;

public class OrderedDto {
	private Long orderNumber;
	private Long userId;
	private String userName;
	private Set<ProductItemDTo> productItemDTo;
	
	public OrderedDto() {
	}
	public OrderedDto(Long orderNumber, Long userId, String userName) {
		super();
		this.orderNumber = orderNumber;
		this.userId = userId;
		this.userName = userName;
		//this.productItem = productItem;
	}
	
	public OrderedDto(Long orderNumber, Long userId, String userName, Set<ProductItemDTo> productItemDTo) {
		this.orderNumber = orderNumber;
		this.userId = userId;
		this.userName = userName;
		this.productItemDTo = productItemDTo;
	}
	public Long getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Set<ProductItemDTo> getProductItemDTo() {
		return productItemDTo;
	}
	public void setProductItemDTo(Set<ProductItemDTo> productItemDTo) {
		this.productItemDTo = productItemDTo;
	}
	@Override
	public String toString() {
		return "OrderedDto [orderNumber=" + orderNumber + ", userId=" + userId + ", userName=" + userName
				+ ", productItemDTo=" + productItemDTo + "]";
	}
	
}
