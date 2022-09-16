package com.example.pharmacy.service;

import java.util.Collection;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.example.pharmacy.form.SalesForm;
import com.example.pharmacy.view.SalesDetailView;
import com.example.pharmacy.view.SalesListView;

@Service
public interface SalesService {
    
    Collection<SalesListView> list();
    SalesDetailView add(SalesForm form);

    SalesDetailView get(Integer salesId) throws NotFoundException;
    Collection<SalesListView> findPaginated(Integer pageNo, Integer pageSize);
    
}
