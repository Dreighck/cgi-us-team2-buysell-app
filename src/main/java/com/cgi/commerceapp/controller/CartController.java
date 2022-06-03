package com.cgi.commerceapp.controller;

import com.cgi.commerceapp.exceptions.ProductWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.cgi.commerceapp.exceptions.CartWithTheIDAlreadyExistsException;
import com.cgi.commerceapp.exceptions.CartWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.Cart;
import com.cgi.commerceapp.service.CartService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

   @Autowired
   CartService cartService;


@GetMapping({"/",""})
public ResponseEntity<List<Cart>> getAllCarts() {
   List<Cart> carts = cartService.getAllCart();
   ResponseEntity<List<Cart>> responseEntity;
   responseEntity = new ResponseEntity<>(carts, HttpStatus.OK);
   return responseEntity;
}

@GetMapping(value = {"/{cartId}"})
public ResponseEntity<?> getCart(@PathVariable("cartId") String cartId) {
   List<Product> productsList;
   ResponseEntity<?> responseEntity;
   try {
      productsList = cartService.getCartById(cartId).getProducts();
      responseEntity = new ResponseEntity<>(productsList, HttpStatus.OK);
   }catch (CartWithTheIDDoesntExistException e){
      responseEntity = new ResponseEntity<>("Cart not found",HttpStatus.NOT_FOUND);
   }
   return responseEntity;
}
@GetMapping(value = {"/{cartId}/{prodId}"})
   public ResponseEntity<?> getCart(@PathVariable("cartId") String cartId,@PathVariable("prodId") int id) {
      Product product;
      ResponseEntity<?> responseEntity;
      try {
         product = cartService.getCartById(cartId).getProductById(id);
         responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
      }catch (CartWithTheIDDoesntExistException e){
         responseEntity = new ResponseEntity<>("Cart not found",HttpStatus.NOT_FOUND);
      } catch (ProductWithTheIDDoesntExistException e) {
         throw new RuntimeException(e);
      }
      return responseEntity;
   }

@PostMapping({"/", "" })
public ResponseEntity<?> addCartHandler(@RequestBody Cart cart)  {
   ResponseEntity<?> responseEntity;
   try{
      cartService.createNewCart(cart);
      responseEntity = new ResponseEntity<>(cart, HttpStatus.CREATED);}
   catch (CartWithTheIDAlreadyExistsException e) {
      responseEntity = new ResponseEntity<>("Cart already exists",HttpStatus.NOT_ACCEPTABLE);
   }
   return responseEntity;
}
   @PostMapping({"/{cartId}", "" })
   public ResponseEntity<?> addCartHandler(@PathVariable("cartId") String cartId)  {
      ResponseEntity<?> responseEntity;
      try{
         cartService.createNewCart(cartId);
         responseEntity = new ResponseEntity<>("Cart created!", HttpStatus.OK);}
      catch (CartWithTheIDAlreadyExistsException e) {
         responseEntity = new ResponseEntity<>("Cart already exists",HttpStatus.NOT_ACCEPTABLE);
      }
      return responseEntity;
   }
@PutMapping("/{cartId}")
public ResponseEntity<?> addProductToCartHandler(@PathVariable("cartId") String id,@RequestBody Product product) {
   cartService.addProductToCart(id,product);
   return new ResponseEntity<>(HttpStatus.ACCEPTED);
}

//@PutMapping({"/{cartId}"})
//public ResponseEntity<?> updateCartHandler(@RequestBody Cart cart,@PathVariable("cartId") int id) {
//   ResponseEntity<?> responseEntity;
//   Cart updatedCart;
//   try{
//      updatedCart = cartService.getCartById(id).updateCart(cart);
//      responseEntity = new ResponseEntity<> (updatedCart, HttpStatus.ACCEPTED);
//   } catch (CartWithTheIDDoesntExistException e){
//      responseEntity = new ResponseEntity<>("Cart doesn't exist",HttpStatus.NOT_ACCEPTABLE);
//   }
//   return responseEntity;
//}

@DeleteMapping("/{cartId}")
public ResponseEntity<?> deleteCartHandler(@PathVariable("cartId") String id)
      throws CartWithTheIDDoesntExistException {
   cartService.deleteCartById(id);
   return new ResponseEntity<>(HttpStatus.OK);
}
@DeleteMapping("/{cartId}/{prodId}")
   public ResponseEntity<?> removeProductFromCartHandler( @PathVariable("cartId") String cartId, @PathVariable int prodId)
      throws CartWithTheIDDoesntExistException, ProductWithTheIDDoesntExistException {
      Product product= cartService.getCartById(cartId).getProductById(prodId);
      cartService.removeProductFromCart(cartId,product);
      return new ResponseEntity<>(HttpStatus.OK);

   }
}
