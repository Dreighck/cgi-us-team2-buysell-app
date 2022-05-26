package com.cgi.commerceapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cart {
  
	@Id
	private int cartNumber;
	private int userId;
	private List<Product> products;
	
	
	public Cart(int cartNumber, int userId, List<Product> products) {
		this.cartNumber = cartNumber;
		this.userId = userId;
		this.products = products;
	}
	
	public Cart() {}
	
	public int getCartNumber() {
		return cartNumber;
	}
	public void setCartNumber(int cartNumber) {
		this.cartNumber = cartNumber;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Cart [cartNumber=" + cartNumber + ", userId=" + userId + ", products=" + products + "]";
	}
	
	
}
