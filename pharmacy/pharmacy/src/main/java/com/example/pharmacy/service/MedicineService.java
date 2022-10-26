package com.example.pharmacy.service;

import java.util.Collection;

import com.example.pharmacy.exception.NotFoundException;
import com.example.pharmacy.extra.Pager;
import com.example.pharmacy.form.MedicineForm;
import com.example.pharmacy.view.MedicineDetailView;
import com.example.pharmacy.view.MedicineListView;

public interface MedicineService {

    Collection<MedicineListView> list();
    Collection<MedicineListView> listExpired();
    
    Collection<MedicineListView> listbyCompanyId(Integer companyId);
    MedicineDetailView add(MedicineForm form);

    MedicineDetailView get(Integer medicineId) throws NotFoundException;

    MedicineDetailView update(Integer medicineId, MedicineForm form) throws NotFoundException;

    void delete(Integer companyId) throws NotFoundException;
    
    Pager<MedicineListView> lists(Integer page, Integer limit, String sortBy, Boolean desc, String filter,
            String search);
    
}