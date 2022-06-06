package com.cgi.commerceapp.service;

import com.cgi.commerceapp.exceptions.UserAccountWithTheIDAlreadyPresentException;
import com.cgi.commerceapp.exceptions.UserAccountWithTheIDDoesntExistException;
import com.cgi.commerceapp.model.User;
import com.cgi.commerceapp.repo.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @Override
//    public User getUserById(String id) throws UserAccountWithTheIDDoesntExistException {
//        Optional<User> optional = userRepository.findById(id);
//        if(optional.isPresent()) {
//            return optional.get();
//        }
//        throw new UserAccountWithTheIDDoesntExistException();
//    }

    @Override
    public User findByUsername(String username) throws UserAccountWithTheIDDoesntExistException {
        Optional<User> optional = userRepository.findByUsername(username);
        if(optional.isPresent()) {
            return optional.get();
        }
        throw new UserAccountWithTheIDDoesntExistException();
    }


    @Override
    public User addNewUser(User user) throws UserAccountWithTheIDAlreadyPresentException {
        Optional<User> optional = userRepository.findByUsername(user.getUsername());

        if(optional.isEmpty()) {
            userRepository.save(user);
            return user;
        }
        throw new UserAccountWithTheIDAlreadyPresentException();
    }

    @Override
    public void deleteUser(String id) throws UserAccountWithTheIDDoesntExistException {
        Optional<User> optional = userRepository.findByUsername(id);
        if (optional.isPresent()){
            userRepository.deleteUserByUsername(id);
        }
        throw new UserAccountWithTheIDDoesntExistException();
    }

    @Override
    public User updateUser(User user) throws UserAccountWithTheIDDoesntExistException {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()){
           userRepository.save(user);

            return userOptional.get();
        }
        throw new UserAccountWithTheIDDoesntExistException();
    }

    public User verifyUser(String username, String password)  throws UserAccountWithTheIDDoesntExistException{
    	
    	Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);
    	if(userOptional.isPresent()) {
    		return userOptional.get();
    	}
    	throw new UserAccountWithTheIDDoesntExistException();
    	}

	@Override
	public String generateToken(User user) {
        String jwtToken;
        jwtToken = Jwts.builder()
                        .setSubject(user.getUsername())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + 500000))
                        .signWith(SignatureAlgorithm.HS256, "cgiusa")
                        .compact();
        return jwtToken;
}

//    @Override
//    public User findByAccountNumber(int id) throws UserAccountWithTheIDDoesntExistException {
//        Optional<User> optional = userRepository.findByAccountNumber(id);
//        if(optional.isPresent()) {
//            return optional.get();
//        }
//        throw new UserAccountWithTheIDDoesntExistException();
//    }
}
    


