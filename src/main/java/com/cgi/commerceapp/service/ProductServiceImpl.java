package com.cgi.commerceapp.service;


import com.cgi.commerceapp.exceptions.CartWithTheIDDoesntExistException;
import com.cgi.commerceapp.exceptions.ProductWithTheIDAlreadyExistsException;
import com.cgi.commerceapp.exceptions.ProductWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.Cart;
import com.cgi.commerceapp.model.Product;
import com.cgi.commerceapp.repo.CartRepo;
import com.cgi.commerceapp.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CartRepo cartRepo;

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(int id) throws ProductWithTheIDDoesntExistException {
        Optional<Product> optional = productRepo.findById(id);
        if (optional.isPresent())
            return optional.get();
        throw new ProductWithTheIDDoesntExistException();
    }

    @Override
    public Product addNewProduct(Product product) throws ProductWithTheIDAlreadyExistsException {
        Optional<Product> optional = productRepo.findById(product.getId());
        if (optional.isEmpty()) {
            return productRepo.save(product);
        }
        throw new ProductWithTheIDAlreadyExistsException();
    }

    @Override
    public void deleteProduct(int id) throws ProductWithTheIDDoesntExistException {
        Optional<Product> optional = productRepo.findById(id);
        if (optional.isEmpty())
            throw new ProductWithTheIDDoesntExistException();
        productRepo.deleteById(id);
    }

    @Override
    public Product updateProduct(Product product) throws ProductWithTheIDDoesntExistException {
        Optional<Product> optional = productRepo.findById(product.getId());
        if (optional.isPresent()) {
            return productRepo.save(product);
        }
        throw new ProductWithTheIDDoesntExistException();
    }

    @Override
    public void removeProductFromCart(int productId, int cartId) throws ProductWithTheIDDoesntExistException, CartWithTheIDDoesntExistException {
        Product product;
        Cart cart;
        if (productRepo.findById(productId).isPresent())
            product = productRepo.findById(productId).get();
        else throw new ProductWithTheIDDoesntExistException();
        if (cartRepo.findById(cartId).isPresent()) {
            cart = cartRepo.findById(cartId).get();
            cart.removeProduct(product);
        } else throw new CartWithTheIDDoesntExistException();
//        List<Product> list = cart.getProducts();
//        list.remove(product);
//        cart.setProducts(list);
        cartRepo.save(cart);
    }

    @Override
    public void addProductToCart(int productId, int cartId)
            throws ProductWithTheIDAlreadyExistsException,
            ProductWithTheIDDoesntExistException,
            CartWithTheIDDoesntExistException {
        Product product;
        Cart cart;
        if (productRepo.findById(productId).isPresent())
            product = productRepo.findById(productId).get();
        else throw new ProductWithTheIDDoesntExistException();
        if (cartRepo.findById(cartId).isPresent())
            cart = cartRepo.findById(cartId).get();
        else throw new CartWithTheIDDoesntExistException();
        List<Product> products = cart.getProducts();
        for (Product prod : products) {
            if (prod.getId() == (product.getId()))
                throw new ProductWithTheIDAlreadyExistsException();
        }
        cart.addProduct(product);
        cartRepo.save(cart);
    }
//        List<Product> list = cart.getProducts();
//        list.add(product);
//        cart.setProducts(list);
}

    

