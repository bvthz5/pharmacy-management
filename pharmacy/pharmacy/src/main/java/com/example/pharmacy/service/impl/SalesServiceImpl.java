package com.example.pharmacy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


// import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.example.pharmacy.enitity.Sales;
import com.example.pharmacy.form.SalesForm;
import com.example.pharmacy.repository.MedicineRepository;
import com.example.pharmacy.repository.SalesRepository;
import com.example.pharmacy.security.util.SecurityUtil;
import com.example.pharmacy.service.SalesService;
import com.example.pharmacy.view.SalesDetailView;
import com.example.pharmacy.view.SalesListView;


@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesRepository salesRepository;
    @Autowired  
    private MedicineRepository medicineRepository;


    @Override
    public Collection<SalesListView> list(){
    	  
          return salesRepository.findByUserUserIdAndStatus(SecurityUtil.getCurrentUserId(),Sales.Status.ACTIVE.value)
          .stream().map(x-> new SalesListView(x)).collect(Collectors.toList());
          
          
        // return null;
    }

    @Override
    public SalesDetailView add(SalesForm form){
   System.out.println("jkgsdjgjksdb->"+form.getSalesQuantity());
    	try {
    		Integer x=	 medicineRepository.editStock(form.getSalesQuantity(), form.getMedicineId());
    		System.out.println("============"+x);
    		if(x==1) {
    		return	new SalesDetailView(salesRepository.save(new Sales(form, SecurityUtil.getCurrentUserId())));
    		}
    		else {
    			throw new ArithmeticException();
    		}
    	}
    	catch(Exception e) {
    		System.out.println("=====================");
    		throw new ArithmeticException();
    		
    	}

       

    	
    }

    @Override
    public SalesDetailView get(Integer salesId) throws NotFoundException {
        return salesRepository.findBySalesIdAndUserUserIdAndStatus( salesId, SecurityUtil.getCurrentUserId(),  Sales.Status.ACTIVE.value)
        .map((sales)->{
            return new SalesDetailView(sales);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
	public Collection<SalesListView> findPaginated(Integer pageNo, Integer pageSize,String sortBy) {
		Pageable paging =  PageRequest.of(pageNo, pageSize,Sort.by("totalAmount").ascending());
		Page<SalesListView> pagedResult = salesRepository.findAllByUserUserId(SecurityUtil.getCurrentUserId(),paging);
		return pagedResult.toList();
    }


    @Override
    public void whenFindByPublicationDate_thenArticles1And2Returned() {
        List<Sales> result = salesRepository.findAllBysalesDate(
          new SimpleDateFormat("yyyy-MM-dd").parse("2022-09-15"));

        assertEquals(2, result.size());
        assertTrue(result.stream()
          .map(Sales::getId)
          .allMatch(id -> Arrays.asList(1, 2).contains(id)));
    }

    @Override
    public void whenFindByPublicationTimeBetween_thenArticles2And3Returned() {
        List<Sales> result = salesRepository.findAllBysalesDateBetween(
          new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2022-09-19 15:15"),
          new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2022-09-15 16:30"));

        assertEquals(2, result.size());
        assertTrue(result.stream()
          .map(Sales::getId)
          .allMatch(id -> Arrays.asList(2, 3).contains(id)));
    }

    @Override
    public void givenArticlesWhenFindWithCreationDateThenArticles2And3Returned() {
        List<Sales> result = salesRepository.findAllWithsalesDateBefore(
          new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2022-09-19 10:00"));

        assertEquals(2, result.size());
        assertTrue(result.stream()
          .map(Sales::getId)
          .allMatch(id -> Arrays.asList(2, 3).contains(id)));
    }
    // @Override
    // public List<SalesListView> findAllOrderBySalesIdAsc() {
    //     var sort;
        
    //     sort = Sort.by(Sort.Direction.ASC, "salesId");
        
    //     return salesRepository.findAllOrderBySalesIdAsc(sort);
    // }


//     @Override
//     public Page<Sales> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
//     Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//      Sort.by(sortField).descending();
 
//     Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//     return this.salesRepository.findAllByUserUserId(pageable);
// }
}
