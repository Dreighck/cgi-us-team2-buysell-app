package com.cgi.commerceapp.repo;



import com.cgi.commerceapp.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cgi.commerceapp.model.Cart;

import java.util.Optional;

@Repository
public interface CartRepo extends MongoRepository<Cart, String>{
//    Cart findByItemNumber(Long itemNumber);
    //


   Optional<Cart> findCartByCartNumber(int id);

}
