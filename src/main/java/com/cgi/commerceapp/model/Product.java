package com.cgi.commerceapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;

@Document
public class Product {
  
	@Id
    private int id;
    private String name;
    private double price;
	private String brand;
    private String description;

	private String imageURL;

	public Product() {}
	public Product(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public Product(int id, String name, double price, String brand) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.brand = brand;
	}

	public Product(int id, String name, double price, String brand, String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.description = description;
	}

	public Product(int id, String name, double price, String brand, String description, String imageURL) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.description = description;
		this.imageURL = imageURL;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + "]";
	}


	public Product updateProduct(Product product) {
		this.id= product.getId();
		this.name=product.getName();
		this.price= product.getPrice();
		this.description= product.getDescription();
		return this;
	}
}

