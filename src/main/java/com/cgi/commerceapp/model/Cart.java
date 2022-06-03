package com.cgi.commerceapp.model;

import java.util.ArrayList;
import java.util.List;

import com.cgi.commerceapp.exceptions.ProductWithTheIDDoesntExistException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Cart {
  
	@Id
	private String cartId;
	private List<Product> products = new ArrayList<>();

	public Cart(String cartId, List<Product> products) {
		this.cartId = cartId;

		this.products = products;
	}
	
	public Cart() {}

	public Cart(String cartId) {
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}
	public void setCartNumber(String cartId) {
		this.cartId = cartId;
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
		return "Cart [cartNumber=" + cartId + /*", userId=" + userId + */", products=" + products + "]";
	}
	
	public Cart updateCart(Cart cart) {
		this.cartId = cart.getCartId();
		this.products = cart.getProducts();
//		this.userId = cart.getUserId();
		return this;
	}
}
