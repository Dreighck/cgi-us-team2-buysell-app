package com.cgi.commerceapp.service;

import com.cgi.commerceapp.exceptions.ProductWithTheIDAlreadyExistsException;
import com.cgi.commerceapp.exceptions.ProductWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.Product;
import com.cgi.commerceapp.repo.ProductRepo;
import com.cgi.commerceapp.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionServImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;
    private ProductRepo productRepo;

        @Override
        public List<Product> getAllProducts() {
            return transactionRepo.findAll();
        }

        @Override
        public Product getProductById(int id) throws ProductWithTheIDDoesntExistException {
            Optional<Product> optional = transactionRepo.findById(id);
            if (optional.isPresent())
                return transactionRepo.getReferenceById(id);
            throw new ProductWithTheIDDoesntExistException();
        }

        @Override
        public Product addNewProduct(Product product) throws ProductWithTheIDAlreadyExistsException {
            Optional<Product> optional = transactionRepo.findById(product.getId());
            if (optional.isEmpty()){
                return transactionRepo.save(product);
            }
            throw new ProductWithTheIDAlreadyExistsException();
        }

        @Override
        public void purchaseProduct(int id) throws ProductWithTheIDDoesntExistException {
            Optional<Product> optional = transactionRepo.findById(id);
            if (optional.isEmpty())
                throw new ProductWithTheIDDoesntExistException();
            transactionRepo.deleteById(id);
            productRepo.deleteById(id);

        }
    }


