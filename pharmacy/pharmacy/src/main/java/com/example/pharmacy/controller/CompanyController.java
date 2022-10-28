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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.extra.Pager;
import com.example.pharmacy.form.CompanyForm;
import com.example.pharmacy.service.CompanyService;
import com.example.pharmacy.view.CompanyDetailView;
import com.example.pharmacy.view.CompanyListView;
import com.example.pharmacy.view.UserView;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/getCompanyList")
    public Collection<CompanyListView> list(Principal p) {
        return companyService.list();
    }

    @PostMapping
    public CompanyDetailView add(@Valid @RequestBody CompanyForm form) {
        return companyService.add(form);
    }

    @GetMapping("/{companyId}")
    public CompanyDetailView get(@PathVariable("companyId") Integer companyId) {
        return companyService.get(companyId);
    }

    @PutMapping("/{companyId}")
    public CompanyDetailView update(@PathVariable("companyId") Integer companyId,
            @Valid @RequestBody CompanyForm form) {
        return companyService.update(companyId, form);
    }

    @DeleteMapping("/{companyId}")
    public void delete(@PathVariable("companyId") Integer companyId) {
        companyService.delete(companyId);
    }

    @GetMapping()
    public Pager<CompanyListView> lists(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
        
            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = false, defaultValue = "company_id") String sortBy,
            @RequestParam(name = "desc", required = false, defaultValue = "false") Boolean desc,
            @RequestParam(name = "filter", required = false, defaultValue = "0") String filter,
            @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        return companyService.lists(page, limit, sortBy, desc, filter, search);
    }

    @GetMapping("/count")
    public long Count() {
        return companyService.Count();
    }

    @DeleteMapping("/activate/{companyId}")
    public void activate(@PathVariable("companyId") Integer companyId){
         companyService.activate(companyId);
    }

    @DeleteMapping("/delete/{companyId}")
    public void ondelete(@PathVariable("companyId") Integer companyId){
        companyService.ondelete(companyId);
    }

    
}