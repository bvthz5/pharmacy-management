package com.example.pharmacy.repository;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

// import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.repository.Repository;
import com.example.pharmacy.enitity.Sales;
import com.example.pharmacy.view.SalesListView;

public interface SalesRepository extends PagingAndSortingRepository<Sales, Integer>
{

    Collection<Sales>findByUserUserIdAndStatus(Integer currentUserId, byte value);
    Sales save(Sales sales);

    Optional<Sales>findBySalesIdAndUserUserIdAndStatus(Integer salesId, Integer currentUserId, byte value); 
    Page<SalesListView> findAllByUserUserId(Integer userId,Pageable paging);
    Iterable<SalesListView> findAllByUserUserId(Integer userId,Sort sort);


    List<Sales> findAllBysalesDate(Date salesDate);

    List<Sales> findAllBysalesDateBetween(
      Date salesDateStart,
      Date salesDateEnd);

    @Query("select a from Sales a where a.salesDate <= :salesDate")
    List<Sales> findAllWithsalesDateBefore(
      @Param("salesDate") Date salesDate);
   
    // public List<Sales> getFilteredData(Sales sales);
    // Page<Sales> findAllByUserUserId(Pageable pageable);  
    // @Query("FROM Sales")
    // List<Sales> findAllOrderBySalesIdAsc(Sort sort);

}
