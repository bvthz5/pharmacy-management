package com.example.pharmacy.controller;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharmacy.extra.Pager;
import com.example.pharmacy.form.MedicineForm;
import com.example.pharmacy.service.MedicineService;
import com.example.pharmacy.view.MedicineDetailView;
import com.example.pharmacy.view.MedicineListView;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController 
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
     MedicineService medicineService;

    @GetMapping
    public Collection<MedicineListView> list(Principal p){
        return medicineService.list();
    }

    @GetMapping("expired")
    public Collection<MedicineListView> listExpired(Principal p){
        return medicineService.listExpired();
    }
    @GetMapping("/getCompany/{comapnyId}")
    public Collection<MedicineListView> listbyCompanyId(@PathVariable("comapnyId") Integer comapnyId,Principal p){
        return medicineService.listbyCompanyId(comapnyId);
    }


    @PostMapping
    public MedicineDetailView add(@Valid @RequestBody MedicineForm form){
        return medicineService.add(form);
    }
    
    @GetMapping("/{medicineId}")
    public MedicineDetailView get(@PathVariable("medicineId")Integer medicineId){
        return medicineService.get(medicineId);
    }   

    @PutMapping("/{medicineId}")
    public MedicineDetailView update(@PathVariable("medicineId") Integer medicineId, @Valid @RequestBody MedicineForm form){
        return medicineService.update(medicineId, form);
    }

    @DeleteMapping("/{medicineId}")
    public void delete(@PathVariable("medicineId") Integer medicineId){
    	medicineService.delete(medicineId);
    }


    @GetMapping("/pager")
    public Pager<MedicineListView> lists(@RequestParam(name="page", required = false, defaultValue = "1") Integer page,
    @RequestParam(name = "limit", required = false, defaultValue ="10") Integer limit,
    @RequestParam(name="sortBy", required = false, defaultValue = "medicine_id") String sortBy,
    @RequestParam(name = "desc", required = false, defaultValue = "false") Boolean desc,
    @RequestParam(name = "filter", required = false, defaultValue = "0") String filter,
    @RequestParam(name = "search", required = false, defaultValue = "") String search){
        return medicineService.lists(page,limit,sortBy,desc,filter,search);
    }


   
    
}