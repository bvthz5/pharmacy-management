package com.example.pharmacy.controller;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.form.CompanyForm;
import com.example.pharmacy.service.CompanyService;
import com.example.pharmacy.view.CompanyDetailView;
import com.example.pharmacy.view.CompanyListView;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public Collection<CompanyListView>list(Principal p){
        return companyService.list();
    }

    @PostMapping
    public CompanyDetailView add(@Valid @RequestBody CompanyForm form){
        return companyService.add(form);
    }

    @GetMapping("/{companyId}")
    public CompanyDetailView get(@PathVariable("companyId") Integer companyId){
        return companyService.get(companyId);
    }

    @PutMapping("/{companyId}")
    public CompanyDetailView update(@PathVariable("companyId") Integer companyId, @Valid @RequestBody CompanyForm form){
        return companyService.update(companyId, form);
    } 

    @DeleteMapping("/{companyId}")
    public void delete(@PathVariable("companyId") Integer companyId){
        companyService.delete(companyId);
    }

    


    
    
}