package com.cgi.commerceapp.controller;

import com.cgi.commerceapp.exceptions.ProductWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

@PostMapping({"/", "","/{cartId}" })
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

@PutMapping({"/{cartId}"})
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

@DeleteMapping("/{cartId}")
public ResponseEntity<?> deleteCartHandler(@PathVariable("cartId") int id)
      throws CartWithTheIDDoesntExistException {
   cartService.deleteCartById(id);
   return new ResponseEntity<>(HttpStatus.GONE);
}
@DeleteMapping("/{cartId}/{prodId}")
   public ResponseEntity<?> removeProductFromCartHandler(@PathVariable("prodId")int prodId, @PathVariable("cartId")int cartId) throws CartWithTheIDDoesntExistException {
      List<Product> productList= new ArrayList<>(cartService.getCartById(cartId).getProducts());
      for(Product product : productList){
         if(product.getId()==prodId){
            cartService.removeProductFromCart(cartId,product);
            return new ResponseEntity<>(product,HttpStatus.GONE);
         }
      }
      return new ResponseEntity<>(prodId,HttpStatus.NOT_FOUND);
   }
}
