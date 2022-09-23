package com.example.pharmacy.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.example.pharmacy.enitity.User;

public interface UserRepository extends Repository<User, Integer> {
    Optional<User> findByEmail(String email); 
    Optional<User> findByUserId(Integer userId);   
    Optional<User> findByUserIdAndPassword(Integer userId, String password);

    
}
