package com.cgi.commerceapp.service;

import java.util.List;
import java.util.Optional;

import com.cgi.commerceapp.exceptions.CartWithTheIDAlreadyExistsException;
import com.cgi.commerceapp.exceptions.CartWithTheIDDoesntExistException;
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
	public Cart getCartById(int id) throws CartWithTheIDDoesntExistException {
		if (cartRepo.findCartByCartNumber(id).isPresent()) return cartRepo.findCartByCartNumber(id).get();
		else throw new CartWithTheIDDoesntExistException();
	}
	public Cart getCartById(String id) throws CartWithTheIDDoesntExistException {
		if (cartRepo.findById(id).isPresent()) return cartRepo.findById(id).get();
		else throw new CartWithTheIDDoesntExistException();
	}

//	@Override
//	public Cart getCartByUserId(int userId) {
//		return cartRepo.getCartByUserId(userId);
//	}
	@Override
	public Cart createNewCart(Cart cart) throws CartWithTheIDAlreadyExistsException {
		Optional<Cart> optional = cartRepo.findById(cart.getCartId());
		if (optional.isEmpty()) {
			return cartRepo.save(cart);
		}
		throw new CartWithTheIDAlreadyExistsException();
	}
	public Cart createNewCart(String cartId) throws CartWithTheIDAlreadyExistsException {
		Cart cart = new Cart(cartId);
		Optional<Cart> optional = cartRepo.findById(cart.getCartId());
		if (optional.isEmpty()) {
			return cartRepo.save(cart);
		}
		throw new CartWithTheIDAlreadyExistsException();
	}


	@Override
	public void deleteCartById(String cartId) throws CartWithTheIDDoesntExistException {
		if (cartRepo.findById(cartId).isPresent()) cartRepo.delete(cartRepo.findById(cartId).get());
		else throw new CartWithTheIDDoesntExistException();
	}

	@Override
	public double getCostOfCartProducts(int id) throws CartWithTheIDDoesntExistException {
		Cart cart;
		if (cartRepo.findCartByCartNumber(id).isPresent()) cart = cartRepo.findCartByCartNumber(id).get();
		else throw new CartWithTheIDDoesntExistException();

		List<Product> list = cart.getProducts();
		double sum = 0;
		for (Product product : list) {
			sum  += product.getPrice();
		}
		return sum;
	}

	@Override
	public Cart updateCart(Cart cart) throws CartWithTheIDDoesntExistException {
		if (cartRepo.findById(cart.getCartId()).isPresent())
			cartRepo.save(cart);
		else throw new CartWithTheIDDoesntExistException();
		return cart;
	}

	@Override
	public Cart addProductToCart(String cartId, Product product){
		Cart cart = cartRepo.findById(cartId).get();
		cart.addProduct(product);
		return cartRepo.save(cart);
	}
	@Override
	public void removeProductFromCart(String cartId, Product product){
		Cart cart = cartRepo.findById(cartId).get();
		cart.removeProduct(product);
		cartRepo.save(cart);
	}

}

