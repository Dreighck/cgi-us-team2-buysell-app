package com.cgi.commerceapp.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cgi.commerceapp.model.Product;


@Repository
public interface ProductRepo extends MongoRepository<Product, Integer> {
}
