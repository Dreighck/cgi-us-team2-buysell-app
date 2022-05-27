package com.cgi.commerceapp.service;


import java.util.List;

import com.cgi.commerceapp.exceptions.CartWithTheIDAlreadyExistsException;
import com.cgi.commerceapp.exceptions.CartWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgi.commerceapp.model.Cart;

@Service
@Transactional
public interface CartService {

   List<Cart> getAllCart();
   Cart getCartById(int id) throws CartWithTheIDDoesntExistException;
   Cart getCartByUserId(int userId);
   void createNewCart(Cart cart) throws CartWithTheIDAlreadyExistsException;
   void deleteCartById(int cartId) throws CartWithTheIDDoesntExistException;
   double getCostOfCartProducts(int id) throws CartWithTheIDDoesntExistException;

   Cart updateCart(Cart cart) throws CartWithTheIDDoesntExistException;

    void addProductToCart(int userId, Product product);

    void removeProductFromCart(int userId, Product product);
}
