package com.cgi.commerceapp.service;


import com.cgi.commerceapp.exceptions.ProductWithTheIDAlreadyExistsException;
import com.cgi.commerceapp.exceptions.ProductWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.Cart;
import com.cgi.commerceapp.model.Product;
import com.cgi.commerceapp.repo.CartRepo;
import com.cgi.commerceapp.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

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
        if (optional.isEmpty()){
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
        if (optional.isPresent()){
            return productRepo.save(product);
        }
        throw new ProductWithTheIDDoesntExistException();
    }


    //accessing prod repo and cart repo
	@Override
	public void removeProductFromCart(int productId, int cartId) throws ProductWithTheIDDoesntExistException {
		// TODO Auto-generated method stub
//		get the cart from the repo
//		reovmove the product from the cart's product list
//		save the new cart back to the repo

		Product product = productRepo.findById(productId).get();
        Cart cart = cartRepo.findById(cartId).get();
        List<Product> list = cart.getProducts();
        list.remove(product);
        cart.setProducts(list);
        cartRepo.save(cart);
	}

	@Override
	public Product addProductToCart(int productId, int cartId) throws ProductWithTheIDAlreadyExistsException {
        // TODO Auto-generated method stub
//		get the cart from the repo
//		add the new product to the cart's product list
//		save the cart back to the repo
        Product product = productRepo.findById(productId).get();
        Cart cart = cartRepo.findById(cartId).get();
        List<Product> list = cart.getProducts();
        list.add(product);
        cart.setProducts(list);
        cartRepo.save(cart);
        return product;
    }
    
}
