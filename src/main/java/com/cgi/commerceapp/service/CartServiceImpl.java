package com.cgi.commerceapp.service;

import java.util.List;

import com.cgi.commerceapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.commerceapp.model.Cart;
import com.cgi.commerceapp.repo.CartRepo;
import com.cgi.commerceapp.repo.ProductRepo;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	ProductRepo productRepo;

	@Override
	public List<Cart> getAllCart() {
		
		return cartRepo.findAll();
	}

	@Override
	public Cart getCartById(int id) {
		
		return cartRepo.findById(id).get();
	}

	@Override
	public Cart getCartByUserId(int userId) {
		
		return cartRepo.getCartByUserId(userId);
	}

	@Override
	public Cart createNewCart(Cart cart) {
		
		cartRepo.save(cart);
		return cart;
	}

	@Override
	public void deleteCartById(int cartId) {
		
		cartRepo.delete(cartRepo.findById(cartId).get());
	}

	@Override
	public double getCostofCartProducts(int id) {
		
		Cart cart = cartRepo.findById(id).get();
		List<Product> list = cart.getProducts();
		double sum = 0;
		for (Product product : list) {
			sum  += product.getPrice();
		}
		return sum;
	}

	

}
