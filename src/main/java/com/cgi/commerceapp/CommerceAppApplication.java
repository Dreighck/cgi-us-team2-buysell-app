package com.cgi.commerceapp;

import com.cgi.commerceapp.model.Cart;
import com.cgi.commerceapp.model.Product;
import com.cgi.commerceapp.service.CartService;
import com.cgi.commerceapp.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class CommerceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommerceAppApplication.class, args);}

    @Bean
        CommandLineRunner runner(ProductService productService){
        return args -> {
//            productService.addNewProduct(new Product( 1,"TV Set", 300.00, "Samsung"));
//            productService.addNewProduct(new Product(2,"Game Console", 200.00));
//            productService.addNewProduct(new Product(3, "Sofa", 100.00));
//            productService.addNewProduct(new Product(4, "Icecream", 5.00));
//            productService.addNewProduct(new Product(5, "Beer", 3.00));
//            productService.addNewProduct(new Product(6, "Phone", 500.00, "iPhone"));
//            productService.addNewProduct(new Product(7, "Watch", 30.00));
        };
    }
}
