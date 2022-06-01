package com.cgi.commerceapp.service;


import com.cgi.commerceapp.exceptions.CartWithTheIDDoesntExistException;
import com.cgi.commerceapp.exceptions.ProductWithTheIDAlreadyExistsException;
import com.cgi.commerceapp.exceptions.ProductWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(int id) throws ProductWithTheIDDoesntExistException;
    Product addNewProduct(Product product) throws ProductWithTheIDAlreadyExistsException;
    void deleteProduct(int id) throws ProductWithTheIDDoesntExistException;
    Product updateProduct(Product product) throws ProductWithTheIDAlreadyExistsException, ProductWithTheIDDoesntExistException;
    void addProductToCart(int productId, int cartId) throws ProductWithTheIDAlreadyExistsException, IOException, ProductWithTheIDDoesntExistException, CartWithTheIDDoesntExistException;
}