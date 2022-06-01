package com.cgi.commerceapp.service;

import com.cgi.commerceapp.exceptions.UserAccountWithTheIDAlreadyPresentException;
import com.cgi.commerceapp.exceptions.UserAccountWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(String id) throws UserAccountWithTheIDDoesntExistException;

    User addNewUser(User user) throws UserAccountWithTheIDAlreadyPresentException;

    void deleteUser(String id) throws UserAccountWithTheIDDoesntExistException;

    User updateUser(User user) throws UserAccountWithTheIDDoesntExistException;
    User verifyUser(String username, String password) throws UserAccountWithTheIDDoesntExistException;
    String generateToken(User user);

}