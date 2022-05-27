package com.cgi.commerceapp.service;


import java.util.List;

import com.cgi.commerceapp.exceptions.CartWithTheIDDoesntExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgi.commerceapp.model.Cart;

@Service
@Transactional
public interface CartService {

   List<Cart> getAllCart();
   Cart getCartById(int id);
   Cart getCartByUserId(int userId);
   Cart createNewCart(Cart cart);
   void deleteCartById(int cartId) throws CartWithTheIDDoesntExistException;
   double getCostOfCartProducts(int id) throws CartWithTheIDDoesntExistException;
}
