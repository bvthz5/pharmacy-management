package com.example.pharmacy.service;

import java.util.Collection;

import com.example.pharmacy.exception.NotFoundException;
import com.example.pharmacy.extra.Pager;
import com.example.pharmacy.form.CompanyForm;
import com.example.pharmacy.view.CompanyDetailView;
import com.example.pharmacy.view.CompanyListView;

public interface CompanyService {

    Collection<CompanyListView> list();

    CompanyDetailView add(CompanyForm form);

    CompanyDetailView get(Integer companyId) throws NotFoundException;

    CompanyDetailView update(Integer companyId, CompanyForm form) throws NotFoundException;

    void delete(Integer companyId) throws NotFoundException;

    Pager<CompanyListView> lists(Integer page, Integer companyId, Integer limit, String sortBy, Boolean desc, String filter,
            String search);

    long Count();

    
}