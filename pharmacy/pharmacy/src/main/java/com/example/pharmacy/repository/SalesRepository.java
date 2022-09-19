package com.example.pharmacy.repository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import com.example.pharmacy.enitity.Sales;
import com.example.pharmacy.view.SalesListView;

public interface SalesRepository extends Repository<Sales, Integer>{

    Collection<Sales>findByUserUserIdAndStatus(Integer currentUserId, byte value);
    Sales save(Sales sales);

    Optional<Sales>findBySalesIdAndUserUserIdAndStatus(Integer salesId, Integer currentUserId, byte value); 
    Page<SalesListView> findAllByUserUserId(Integer userId,Pageable paging);
    // public List<Sales> getFilteredData(Sales sales);
    // Page<Sales> findAllByUserUserId(Pageable pageable);  
    
}
