package com.example.pharmacy.service;

import java.util.Collection;

import com.example.pharmacy.exception.NotFoundException;
import com.example.pharmacy.extra.Pager;
import com.example.pharmacy.form.CompanyForm;
import com.example.pharmacy.view.CompanyDetailView;
import com.example.pharmacy.view.CompanyListView;
import com.example.pharmacy.view.UserView;

public interface CompanyService {

    Collection<CompanyListView> list();

    CompanyDetailView add(CompanyForm form);

    CompanyDetailView get(Integer companyId) throws NotFoundException;

    CompanyDetailView update(Integer companyId, CompanyForm form) throws NotFoundException;

    void delete(Integer companyId) throws NotFoundException;

    Pager<CompanyListView> lists(Integer page, Integer limit, String sortBy, Boolean desc, String filter,
            String search);

    long Count();

    public void activate(int companyId);

    void ondelete(Integer companyId) throws NotFoundException;

    
}