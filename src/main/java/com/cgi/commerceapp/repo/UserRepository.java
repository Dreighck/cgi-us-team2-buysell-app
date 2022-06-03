package com.cgi.commerceapp.repo;
//
//import com.cgi.commerceapp.model.User;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//
//@Repository
//@Transactional
//public interface UserRepository extends JpaRepository<User, String> {
//
//    Optional<User> findByUsernameAndPassword(String username, String password);
//
//}

import com.cgi.commerceapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsernameAndPassword(String username, String password);
}
