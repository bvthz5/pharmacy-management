package com.example.pharmacy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.example.pharmacy.enitity.User;

public interface UserRepository extends Repository<User, Integer> {
    Optional<User> findByEmail(String email); 
    Optional<User> findById(Integer userId);   
    Optional<User> findByUserIdAndPassword(Integer userId, String password);

    User save(User user);

    Optional<User> findByResetPasswrdToken(String token);

    User findByUserId(Integer userId);

    @Query(value = "select count(*) from user", nativeQuery = true)
    long countUsers();

    
}
