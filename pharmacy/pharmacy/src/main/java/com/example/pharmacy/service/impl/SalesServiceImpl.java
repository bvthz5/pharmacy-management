package com.example.pharmacy.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;

// import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.pharmacy.enitity.Sales;
import com.example.pharmacy.form.SalesForm;
import com.example.pharmacy.repository.SalesRepository;
import com.example.pharmacy.security.util.SecurityUtil;
import com.example.pharmacy.service.SalesService;
import com.example.pharmacy.view.SalesDetailView;
import com.example.pharmacy.view.SalesListView;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesRepository salesRepository;
  
    @Override
    public Collection<SalesListView> list(){
          return salesRepository.findByUserUserIdAndStatus(SecurityUtil.getCurrentUserId(),Sales.Status.ACTIVE.value)
          .stream().map(x-> new SalesListView(x)).collect(Collectors.toList());
          
          
        // return null;
    }

    @Override
    public SalesDetailView add(SalesForm form){
    //System.out.println("jkgsdjgjksdb->"+form.getMedicinename());
        return new SalesDetailView(salesRepository.save(new Sales(form,SecurityUtil.getCurrentUserId())));
    }

    @Override
    public SalesDetailView get(Integer salesId) throws NotFoundException {
        return salesRepository.findBySalesIdAndUserUserIdAndStatus( salesId, SecurityUtil.getCurrentUserId(),  Sales.Status.ACTIVE.value)
        .map((sales)->{
            return new SalesDetailView(sales);
        }).orElseThrow(NotFoundException::new);
    }

    
}
