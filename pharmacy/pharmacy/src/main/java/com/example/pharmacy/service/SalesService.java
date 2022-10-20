package com.example.pharmacy.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.pharmacy.enitity.Pager;
import com.example.pharmacy.enitity.Sales;
import com.example.pharmacy.form.SalesForm;
import com.example.pharmacy.view.SalesDetailView;
import com.example.pharmacy.view.SalesListView;

@Service
public interface SalesService {
    
    Collection<SalesListView> list();
    Collection<SalesListView> add(@Valid Collection<SalesForm> form);

    SalesDetailView get(Integer salesId) throws NotFoundException;
    Collection<SalesListView> findPaginated(Integer pageNo, Integer pageSize,String sortDir, String sortBy);
    Collection<SalesListView> findAllByDateBetween(Date date);
    Pager<SalesListView> findPager(Integer pageSize, Integer numItems, Integer page, Boolean type, String sort);
    // Page<SalesListView> findAllByUserUserId(Pageable pageable);
    
    // Collection<Sales> listAll(String keyword);
    List<SalesListView> list(String search,Integer pageNo, Integer pageSize);
    
}
