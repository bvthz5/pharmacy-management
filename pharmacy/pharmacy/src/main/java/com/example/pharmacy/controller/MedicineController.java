package com.example.pharmacy.controller;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
}