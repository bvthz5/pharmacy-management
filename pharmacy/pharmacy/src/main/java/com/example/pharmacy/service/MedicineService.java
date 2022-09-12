package com.example.pharmacy.service;

import java.util.Collection;

import com.example.pharmacy.exception.NotFoundException;
import com.example.pharmacy.form.MedicineForm;
import com.example.pharmacy.view.MedicineDetailView;
import com.example.pharmacy.view.MedicineListView;

public interface MedicineService {

    Collection<MedicineListView> list();

    MedicineDetailView add(MedicineForm form);

    MedicineDetailView get(Integer medicineId) throws NotFoundException;

    MedicineDetailView update(Integer medicineId, MedicineForm form) throws NotFoundException;

    void delete(Integer companyId) throws NotFoundException;
    
}