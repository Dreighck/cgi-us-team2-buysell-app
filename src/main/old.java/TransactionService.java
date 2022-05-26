package com.cgi.commerceapp.service;


import com.cgi.commerceapp.exceptions.ProductWithTheIDAlreadyExistsException;
import com.cgi.commerceapp.exceptions.ProductWithTheIDDoesntExistException;

import com.cgi.commerceapp.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public interface TransactionService {

    List<Product> getAllProducts();

    Product getProductById(int id) throws ProductWithTheIDDoesntExistException;

    Product addNewProduct(Product product) throws ProductWithTheIDAlreadyExistsException;

    void purchaseProduct(int id) throws ProductWithTheIDDoesntExistException;
}
