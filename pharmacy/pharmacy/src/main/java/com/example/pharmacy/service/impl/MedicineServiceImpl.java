package com.example.pharmacy.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pharmacy.enitity.Medicine;
import com.example.pharmacy.exception.NotFoundException;
import com.example.pharmacy.form.MedicineForm;
import com.example.pharmacy.repository.MedicineRepository;
import com.example.pharmacy.security.util.SecurityUtil;
import com.example.pharmacy.service.MedicineService;
import com.example.pharmacy.view.MedicineDetailView;
import com.example.pharmacy.view.MedicineListView;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Collection<MedicineListView> list(){
          return medicineRepository.findByUserUserIdAndStatus(SecurityUtil.getCurrentUserId(),Medicine.Status.ACTIVE.value)
          .stream().map(x-> new MedicineListView(x)).collect(Collectors.toList());
          
          
        // return null;
    }

    @Override
    public MedicineDetailView add(MedicineForm form){
    //System.out.println("jkgsdjgjksdb->"+form.getMedicinename());
        return new MedicineDetailView(medicineRepository.save(new Medicine(form, SecurityUtil.getCurrentUserId())));
    }

    @Override
    public MedicineDetailView get(Integer medicineId) throws NotFoundException {
        return medicineRepository.findByMedicineIdAndUserUserIdAndStatus( medicineId, SecurityUtil.getCurrentUserId(),  Medicine.Status.ACTIVE.value)
        .map((medicine)->{
            return new MedicineDetailView(medicine);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public MedicineDetailView update(Integer medicineId, MedicineForm form) throws NotFoundException {
        return medicineRepository.findByMedicineIdAndUserUserIdAndStatus( medicineId, SecurityUtil.getCurrentUserId(), Medicine.Status.ACTIVE.value)
        .map((company) -> {
            return new MedicineDetailView(medicineRepository.save(company.update(form)));
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Integer medicineId) throws NotFoundException {
        medicineRepository.deleteMedicine(medicineId);
       
        
    }

   
   
    
}