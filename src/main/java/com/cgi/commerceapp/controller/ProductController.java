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
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	ProductService productService;
	private Product deletedProduct;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> products = productService.getAllProducts();
		ResponseEntity<List<Product>> responseEntity;
		responseEntity = new ResponseEntity<>(products, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping(value = { "/products/{prodID}" })
	public ResponseEntity<Product> getProduct(@PathVariable("prodID") int prodID)
			throws ProductWithTheIDDoesntExistException {
		Product product = productService.getProductById(prodID);
		ResponseEntity<Product> responseEntity;
		responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping({ "/products", "" })
	public ResponseEntity<?> addProductHandler(@RequestBody Product product) {
		ResponseEntity<?> responseEntity;
		try {
			Product prod = new Product();
			prod = productService.addNewProduct(product);
			responseEntity = new ResponseEntity<>(prod, HttpStatus.CREATED);
		} catch (ProductWithTheIDAlreadyExistsException e) {
			responseEntity = new ResponseEntity<>("Failed to store, Duplicate", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}

	@PutMapping({ "/products", "" })
	public ResponseEntity<?> updateProductHandler(@PathVariable("prodId") int id,@RequestBody Product product)throws  ProductWithTheIDDoesntExistException, ProductWithTheIDAlreadyExistsException{
		ResponseEntity<?> responseEntity;
		try{
			product.setId(id);
			Product updatedProduct = productService.updateProduct(product);
			responseEntity = new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);	
		}catch(ProductWithTheIDAlreadyExistsException e) {
				responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
				responseEntity = new ResponseEntity<String>("Failed to update, Product ID already exists.", HttpStatus.ALREADY_REPORTED);
		}catch(ProductWithTheIDDoesntExistException e){
			responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
			responseEntity = new ResponseEntity<String>("Failed to update, Product ID does not exist.", HttpStatus.NOT_FOUND);	
		}
		return responseEntity;
	}

	@DeleteMapping("/products/{prodId}")
	public ResponseEntity<?> deleteProductHandler(@PathVariable("prodId") int id)
			throws ProductWithTheIDDoesntExistException {
				ResponseEntity<?> responseEntity;
				try{
					productService.deleteProduct(id);
					responseEntity = new ResponseEntity<String>("Deleted",HttpStatus.OK);
				}catch(ProductWithTheIDDoesntExistException e){
					responseEntity = new ResponseEntity<String>("Failed to update, Product ID does not exist.", HttpStatus.NOT_FOUND);	
				}
				return responseEntity;

	}

	//add and remove prod to cart
	@DeleteMapping("/carts/{cartId}/{prodId}")
	public void removeProductFromCartHandlEntity(@PathVariable("prodId")int id, int cartId) throws ProductWithTheIDDoesntExistException{
           //Kristen still working this part
	}



}
