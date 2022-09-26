package com.example.pharmacy.repository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.example.pharmacy.enitity.Sales;
import com.example.pharmacy.view.SalesListView;

public interface SalesRepository extends Repository<Sales, Integer>{

    Collection<Sales>findByUserUserIdAndStatus(Integer currentUserId, byte value);
    Collection<Sales>findByStatus( byte value);
    Sales save(Sales sales);
    Page<SalesListView> findAllByUserUserId(Integer userId,Pageable paging);


    Optional<Sales>findBySalesIdAndUserUserIdAndStatus(Integer salesId, Integer currentUserId, byte value); 
    Collection<SalesListView> findAllBysalesDateBetween(Date today, Date days);

    
    
}
