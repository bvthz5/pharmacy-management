package com.example.pharmacy.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.example.pharmacy.enitity.Sales;
import com.example.pharmacy.view.SalesListView;

public interface SalesRepository extends Repository<Sales, Integer>{

    Collection<Sales>findByUserUserIdAndStatus(Integer currentUserId, byte value);
    Collection<Sales>findByStatus( byte value);
    Sales save(Sales sales);
    Page<Sales> findAll(Pageable paging);


    Optional<Sales>findBySalesIdAndUserUserIdAndStatus(Integer salesId, Integer currentUserId, byte value); 
    Collection<SalesListView> findAllBysalesDateBetween(Date today, Date days);

    
   	@Transactional
   	@Query(value = "select * from sales where sales_date = date",nativeQuery = true)
   	Collection<SalesListView> findAllBysalesDate(Date date);
   

    // @Query(value = "select * from sales s where s.sales_id like %:keyword% or s.total_amount like %:keyword%", nativeQuery = true)
    // public Collection<Sales> search(String keyword);


    // @Query(value = "select * from sales s where s.total_amount like %?1% or s.sales_date like %?1%", nativeQuery = true)
    // @Query(value ="select * from sales s where s.sales_id like %?1%" + " or s.total_amount like %?1%" +" or s.medicinename like %?1% ", nativeQuery = true)
    @Query(value = "select * from sales inner join medicine on medicine.medicine_id = sales.medicine_id inner join company on company.company_id = sales.company_id inner join user on user.user_id = sales.user_id where total_amount like %?1% or sales_date like %?1% or medicine.medicinename like %?1% or company.name like %?1% or user.user_id like %?1%" , nativeQuery=true)
    List<Sales> searchKey(String search,Pageable paging);

    @Query(value = "SELECT COUNT(*) FROM sales", nativeQuery = true)
    Long countList(String currentDate);


    @Query(value = "select * from sales", nativeQuery = true)
    Iterable<Sales> getsalesList(String currentDate, PageRequest of);

    



    


}
