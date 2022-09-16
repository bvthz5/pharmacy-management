package com.example.pharmacy.controller;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.pharmacy.form.SalesForm;
import com.example.pharmacy.service.SalesService;
import com.example.pharmacy.view.SalesDetailView;
import com.example.pharmacy.view.SalesListView;
// import com.example.pharmacy.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SalesController {

     @Autowired
    SalesService salesService;

    @GetMapping
    public Collection<SalesListView> list(Principal p){
        return salesService.list();
        
    }
    @GetMapping("/{pageNo}/{pageSize}")
	public Collection<SalesListView> getPaginated(@PathVariable Integer pageNo,@PathVariable Integer pageSize){
		return salesService.findPaginated(pageNo, pageSize);
	}

    @PostMapping("/add")
    public SalesDetailView add(@Valid @RequestBody SalesForm form)
    {
        return salesService.add(form);
    }

    @GetMapping("/{salesId}")
    public SalesDetailView get(@PathVariable("salesId")Integer salesId) throws NotFoundException
    {
        return salesService.get(salesId);
    }    
    
    
}
