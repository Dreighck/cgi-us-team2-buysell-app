package com.cgi.commerceapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity

@Document
@Table(name = "buysell_user")
public class User {
    @Id
    @Column(length = 64)
    private String username;
    private String name;
    private String email;
    
    private String password;
	private int accountNumber;
    public User() {

    }
    public User(String name, String email, String username, String password, int accountNumber) {
        
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
		this.accountNumber=accountNumber;
    }
    
	public String getName() {
		return name;
	}
	public void setName(String firstName) {
		this.name = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String lastName) {
		this.email = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
}
