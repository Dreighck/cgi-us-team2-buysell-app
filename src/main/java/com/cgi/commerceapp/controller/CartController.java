package com.cgi.commerceapp.controller;

import java.util.List;

import com.cgi.commerceapp.exceptions.CartWithTheIDAlreadyExists;
import com.cgi.commerceapp.exceptions.CartWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.Cart;
import com.cgi.commerceapp.service.CartService;

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
public ResponseEntity<Cart> getcart(@PathVariable("cartId") int cartId)
      throws CartWithTheIDDoesntExistException {
   Cart cart = cartService.getCartById(cartId);
   ResponseEntity<Cart> responseEntity;
   responseEntity = new ResponseEntity<>(cart, HttpStatus.OK);
   return responseEntity;
}

@PostMapping({ "/carts", "" })
public ResponseEntity<?> addcartHandler(@RequestBody Cart cart) throws CartWithTheIDAlreadyExists{
   ResponseEntity<?> responseEntity;
   cart = cartService.createNewCart(cart);
   responseEntity = new ResponseEntity<>(cart, HttpStatus.CREATED);
   return responseEntity;
}

@PutMapping({ "/carts", "" })
public ResponseEntity<Cart> updatecartHandler(@RequestBody Cart cart)
      throws CartWithTheIDDoesntExistException, CartWithTheIDAlreadyExists {
   return null;
}

@DeleteMapping("/carts/{cartId}")
public ResponseEntity<?> deletecartHandler(@PathVariable("cartId") int id)
      throws CartWithTheIDDoesntExistException {

   return null;
}



}
