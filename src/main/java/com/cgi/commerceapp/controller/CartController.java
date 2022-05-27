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
@RequestMapping("/api/v1")
public class CartController {

   @Autowired
   CartService cartService;


@GetMapping({"/carts"})
public ResponseEntity<List<Cart>> getAllCarts() {
   List<Cart> carts = cartService.getAllCart();
   ResponseEntity<List<Cart>> responseEntity;
   responseEntity = new ResponseEntity<>(carts, HttpStatus.OK);
   return responseEntity;
}

@GetMapping(value = { "/carts/{cartId}" })
public ResponseEntity<?> getCart(@PathVariable("cartId") int cartId) {
   Cart cart;
   ResponseEntity<?> responseEntity;
   try {
      cart = cartService.getCartById(cartId);
      responseEntity = new ResponseEntity<>(cart, HttpStatus.OK);
   }catch (CartWithTheIDDoesntExistException e){
      responseEntity = new ResponseEntity<>("Cart not found",HttpStatus.NOT_FOUND);
   }
   return responseEntity;
}

@PostMapping({ "/carts", "" })
public ResponseEntity<?> addCartHandler(@RequestBody Cart cart)  {
   ResponseEntity<?> responseEntity;
   try{
      Cart prod = cartService.createNewCart(cart);
      responseEntity = new ResponseEntity<>(prod, HttpStatus.CREATED);}
   catch (CartWithTheIDAlreadyExistsException e) {
      responseEntity = new ResponseEntity<>("Cart already exists",HttpStatus.NOT_ACCEPTABLE);
   }
   return responseEntity;
}

@PutMapping({"/carts/{cartId}" })
public ResponseEntity<?> updateCartHandler(@RequestBody Cart cart,@PathVariable("cartId") int id) {
   ResponseEntity<?> responseEntity;
   Cart updatedCart;
   try{
      updatedCart = cartService.getCartById(id).updateCart(cart);
      responseEntity = new ResponseEntity<> (updatedCart, HttpStatus.ACCEPTED);
   } catch (CartWithTheIDDoesntExistException e){
      responseEntity = new ResponseEntity<>("Cart doesn't exist",HttpStatus.NOT_ACCEPTABLE);
   }
   return responseEntity;
}

@DeleteMapping("/carts/{cartId}")
public ResponseEntity<?> deleteCartHandler(@PathVariable("cartId") int id)
      throws CartWithTheIDDoesntExistException {
   cartService.deleteCartById(id);
   return new ResponseEntity<>(HttpStatus.GONE);
}



}
