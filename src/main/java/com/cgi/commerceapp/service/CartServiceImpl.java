package com.cgi.commerceapp.service;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getCartById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart getCartByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart createNewCart(Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCartById(int cartId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getCostofCartProducts(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
