package com.cgi.commerceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.cgi.commerceapp.exceptions.CartWithTheIDAlreadyExistsException;
import com.cgi.commerceapp.exceptions.CartWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.Cart;
import com.cgi.commerceapp.service.CartService;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

   @Autowired
   CartService cartService;


@GetMapping("/cart")
public ResponseEntity<List<Cart>> getAllCarts() {
   List<Cart> carts = cartService.getAllCart();
   ResponseEntity<List<Cart>> responseEntity;
   responseEntity = new ResponseEntity<>(carts, HttpStatus.OK);
   return responseEntity;
}

@GetMapping(value = { "/carts/{cartId}" })
public ResponseEntity<Cart> getCart(@PathVariable("cartId") int cartId)
      throws CartWithTheIDDoesntExistException {
   Cart cart = cartService.getCartById(cartId);
   ResponseEntity<Cart> responseEntity;
   responseEntity = new ResponseEntity<>(cart, HttpStatus.OK);
   return responseEntity;
}

@PostMapping({ "/carts", "" })
public ResponseEntity<?> addCartHandler(@RequestBody Cart cart)  {
   ResponseEntity<?> responseEntity;
   try{
      Cart prod = cartService.createNewCart(cart);
      responseEntity = new ResponseEntity<>(prod, HttpStatus.CREATED);}
   catch (CartWithTheIDAlreadyExistsException e) {
      throw new RuntimeException(e);
   }
   return responseEntity;
}

@PutMapping({ "/carts", "" })
public ResponseEntity<Cart> updateCartHandler(@RequestBody Cart cart)
      throws CartWithTheIDDoesntExistException, CartWithTheIDAlreadyExistsException {
   return null;
}

@DeleteMapping("/carts/{cartId}")
public ResponseEntity<?> deleteCartHandler(@PathVariable("cartId") int id)
      throws CartWithTheIDDoesntExistException {

   return null;
}



}
