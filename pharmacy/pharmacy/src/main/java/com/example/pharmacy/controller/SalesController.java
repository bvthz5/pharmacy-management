package com.example.pharmacy.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.enitity.Sales;
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
	public Collection<SalesListView> getPaginated(@PathVariable Integer pageNo,@PathVariable Integer pageSize,String sortBy)
    {
		return salesService.findPaginated(pageNo, pageSize,sortBy);
	}

   
    @GetMapping("/filter/{days}")
    public Collection<SalesListView> findAllByDateBetween(@PathVariable Integer days){
        System.out.println(days);
        return salesService.findAllByDateBetween(days);
    }

    // @GetMapping
    // public List<SalesListView> getSalesBySalesId() {

    //     return salesService.findAllOrderBySalesIdAsc();
    // }

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
    

    // @GetMapping("/")
    // public String viewHomePage(Model model) {
    //     return findPaginated(1, "firstName", "asc", model);
    // }

    // @GetMapping("/page/{pageNo}")
    // public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
    //     @RequestParam("sortField") String sortField,
    //     @RequestParam("sortDir") String sortDir,
    //     Model model) {
    //     int pageSize = 5;

    //     Page < Sales > page = salesService.findPaginated(pageNo, pageSize, sortField, sortDir);
    //     List < Sales > listEmployees = page.getContent();

    //     model.addAttribute("currentPage", pageNo);
    //     model.addAttribute("totalPages", page.getTotalPages());
    //     model.addAttribute("totalItems", page.getTotalElements());

    //     model.addAttribute("sortField", sortField);
    //     model.addAttribute("sortDir", sortDir);
    //     model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

    //     model.addAttribute("listEmployees", listEmployees);
    //     return "index";
    // }
    
}
