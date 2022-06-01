package com.cgi.commerceapp;

import com.cgi.commerceapp.controller.CartController;
import com.cgi.commerceapp.model.Cart;
import com.cgi.commerceapp.model.Product;
import com.cgi.commerceapp.repo.CartRepo;
import com.cgi.commerceapp.repo.ProductRepo;
import com.cgi.commerceapp.service.CartService;
import com.cgi.commerceapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;


@SpringBootApplication
public class CommerceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommerceAppApplication.class, args);}

    @Autowired
    ProductRepo productRepo;
    @Autowired
    CartRepo cartRepo;
    @Bean
        CommandLineRunner runner(ProductService productService,CartService cartService){
        return args -> {
            Product test = new Product();
            productRepo.deleteAll();
            productService.addNewProduct(new Product( 1,"TV Set", 300.00, "Samsung TV",
                    "GREEEEEEEEEAAAAATTTTT condition, only issue is that all audio is dubbed by Tony the Tiger"));
            productService.addNewProduct(new Product(2,"Game Console", 200.00));
            productService.addNewProduct(new Product(3, "Sofa", 100.00));
            productService.addNewProduct(new Product(4, "Icecream", 5.00, "bordens"));
            productService.addNewProduct(new Product(5, "Beer", 3.00));
            productService.addNewProduct(new Product(6, "Phone", 5000.03, "iPhone", "24 XS Pro Max"));
            productService.addNewProduct(new Product(7, "Watch", 30.00));
            cartRepo.deleteAll();
            cartService.createNewCart(new Cart(1, new ArrayList<>()));
            cartService.addProductToCart(1, new Product(7, "Watch", 30.00, "ikea", ""));
            cartService.addProductToCart(1, new Product(6, "Phone", 5000.03, "iPhone", "24 XS Pro Max"));
            cartService.addProductToCart(1,test);
            cartService.removeProductFromCart(1, test);
        };

    }

}
