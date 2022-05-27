package com.cgi.commerceapp.repo;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cgi.commerceapp.model.Cart;

@Repository
public interface CartRepo extends MongoRepository<Cart, Integer>{
//    Cart findByItemNumber(Long itemNumber);
    //


    Cart getCartByUserId(int id);
}
