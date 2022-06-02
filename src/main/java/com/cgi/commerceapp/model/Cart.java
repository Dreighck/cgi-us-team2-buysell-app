package com.cgi.commerceapp.model;

import java.util.ArrayList;
import java.util.List;

import com.cgi.commerceapp.exceptions.ProductWithTheIDDoesntExistException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Cart {
  
	@Id
	private int cartNumber;
	private List<Product> products = new ArrayList<>();

	public Cart(int cartNumber, List<Product> products) {
		this.cartNumber = cartNumber;

		this.products = products;
	}
	
	public Cart() {}

	public Cart(int cartNumber) {
		this.cartNumber = cartNumber;
	}

	public int getCartNumber() {
		return cartNumber;
	}
	public void setCartNumber(int cartNumber) {
		this.cartNumber = cartNumber;
	}
//	public int getUserId() {
//		return userId;
//	}
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
	public List<Product> getProducts() {
		return products;
	}

	public Product getProductById(int id) throws ProductWithTheIDDoesntExistException {
		for (Product product: this.products) {
			if(product.getId()==id){
				return product;
			}
		}
		throw new ProductWithTheIDDoesntExistException();
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void addProduct(Product product){ this.products.add(product);}
	public void removeProduct(Product product) {
		int id = product.getId();
		this.products.removeIf(prod -> prod.getId() == id);
	}
	public void removeProductById(int ProdId) {
		this.products.removeIf(prod -> prod.getId() == ProdId);
	}
	@Override
	public String toString() {
		return "Cart [cartNumber=" + cartNumber + /*", userId=" + userId + */", products=" + products + "]";
	}
	
	public Cart updateCart(Cart cart) {
		this.cartNumber = cart.getCartNumber();
		this.products = cart.getProducts();
//		this.userId = cart.getUserId();
		return this;
	}
}
