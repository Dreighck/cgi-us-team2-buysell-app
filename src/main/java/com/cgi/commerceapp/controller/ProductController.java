package com.cgi.commerceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.commerceapp.exceptions.ProductWithTheIDAlreadyExistsException;
import com.cgi.commerceapp.exceptions.ProductWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.Product;
import com.cgi.commerceapp.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	ProductService productService;


	@GetMapping({"/",""})
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> product = productService.getAllProducts();
		ResponseEntity<List<Product>> responseEntity;
		responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping(value = {"/{prodID}"})
	public ResponseEntity<Product> getProduct(@PathVariable("prodID") int prodID)
			throws ProductWithTheIDDoesntExistException {
		Product product = productService.getProductById(prodID);
		ResponseEntity<Product> responseEntity;
		responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping({"/", "" })
	public ResponseEntity<?> addProductHandler(@RequestBody Product product) {
		ResponseEntity<?> responseEntity;
		try {
			Product prod=productService.addNewProduct(product);
			responseEntity = new ResponseEntity<>(prod, HttpStatus.CREATED);
		} catch (ProductWithTheIDAlreadyExistsException e) {
			responseEntity = new ResponseEntity<>("Failed to store, Duplicate", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@PutMapping({"/{prodId}" })
	public ResponseEntity<?> updateProductHandler(@PathVariable("prodId") int id, @RequestBody Product product){
		ResponseEntity<?> responseEntity;
		try {
			Product updatedProduct = productService.getProductById(id).updateProduct(product);
			responseEntity = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
		}catch(ProductWithTheIDDoesntExistException e){
			responseEntity = new ResponseEntity<>("Failed to update, Product ID does not exist.", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	@PutMapping({"/", "" })
	public ResponseEntity<?> updateProductHandler(@RequestBody Product product){
		ResponseEntity<?> responseEntity;
		int id = product.getId();
		try {
			Product updatedProduct = productService.getProductById(id).updateProduct(product);
			responseEntity = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
		}catch(ProductWithTheIDDoesntExistException e){
			responseEntity = new ResponseEntity<>("Failed to update, Product ID does not exist.", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@DeleteMapping("/{prodId}")
	public ResponseEntity<?> deleteProductHandler(@PathVariable("prodId") int id) {
				ResponseEntity<?> responseEntity;
				try{
					productService.deleteProduct(id);
					responseEntity = new ResponseEntity<>("Deleted", HttpStatus.OK);
				}catch(ProductWithTheIDDoesntExistException e){
					responseEntity = new ResponseEntity<>("Failed to update, Product ID does not exist.", HttpStatus.NOT_FOUND);
				}
				return responseEntity;
	}
	@DeleteMapping({"/",""})
	public ResponseEntity<?> deleteProductHandler(@RequestBody Product product) {
		ResponseEntity<?> responseEntity;
		int id = product.getId();
		try{
			productService.deleteProduct(id);
			responseEntity = new ResponseEntity<>("Deleted", HttpStatus.OK);
		}catch(ProductWithTheIDDoesntExistException e){
			responseEntity = new ResponseEntity<>("Failed to update, Product ID does not exist.", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
}
