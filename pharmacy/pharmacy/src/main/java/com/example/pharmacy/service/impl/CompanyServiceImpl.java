package com.example.pharmacy.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacy.enitity.Company;
import com.example.pharmacy.exception.NotFoundException;
import com.example.pharmacy.form.CompanyForm;
import com.example.pharmacy.repository.CompanyRepository;
import com.example.pharmacy.security.util.SecurityUtil;
import com.example.pharmacy.service.CompanyService;
import com.example.pharmacy.view.CompanyDetailView;
import com.example.pharmacy.view.CompanyListView;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Collection<CompanyListView> list(){
        return companyRepository.findByUserUserIdAndStatus(SecurityUtil.getCurrentUserId(),Company.Status.ACTIVE.value);
    }

    @Override
    public CompanyDetailView add(CompanyForm form){
        return new CompanyDetailView(companyRepository.save(new Company(form, SecurityUtil.getCurrentUserId())));
    }

    @Override
    public CompanyDetailView get(Integer companyId) throws NotFoundException {
        return companyRepository.findByCompanyIdAndUserUserIdAndStatus(SecurityUtil.getCurrentUserId(), companyId, Company.Status.ACTIVE.value)
        .map((company)->{
            return new CompanyDetailView(company);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public CompanyDetailView update(Integer companyId, CompanyForm form) throws NotFoundException {
        return companyRepository.findByCompanyIdAndUserUserIdAndStatus(SecurityUtil.getCurrentUserId(), companyId, Company.Status.ACTIVE.value)
        .map((company) -> {
            return new CompanyDetailView(companyRepository.save(company.update(form)));
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Integer companyId) throws NotFoundException {
        companyRepository.deleteCompany(companyId);
       
        
    }


   




    
}