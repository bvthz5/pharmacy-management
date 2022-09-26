package com.example.pharmacy.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.example.pharmacy.enitity.Sales;

public interface SalesRepository extends Repository<Sales, Integer>{

    Collection<Sales>findByUserUserIdAndStatus(Integer currentUserId, byte value);
    Collection<Sales>findByStatus( byte value);
    Sales save(Sales sales);

    Optional<Sales>findBySalesIdAndUserUserIdAndStatus(Integer salesId, Integer currentUserId, byte value); 

    
    
}
