package com.example.pharmacy.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.pharmacy.enitity.Medicine;
import com.example.pharmacy.exception.NotFoundException;
import com.example.pharmacy.extra.Pager;
import com.example.pharmacy.form.MedicineForm;
import com.example.pharmacy.repository.MedicineRepository;
import com.example.pharmacy.service.MedicineService;
import com.example.pharmacy.view.MedicineDetailView;
import com.example.pharmacy.view.MedicineListView;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Collection<MedicineListView> list() {
        Date date = new Date();
        return medicineRepository.findByStatusAndQuantityGreaterThanAndExpiryDateGreaterThan(Medicine.Status.ACTIVE.value,0,date)
                .stream().map(x -> new MedicineListView(x)).collect(Collectors.toList());

        // return null;
    }
    @Override
    public Collection<MedicineListView> listbyCompanyId(Integer companyId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MedicineDetailView add(MedicineForm form) {
        // System.out.println("jkgsdjgjksdb->"+form.getMedicinename());
        // System.out.println("=====>"+form.getExpiryDate());
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // try {
        //     Date date1 = sdf.parse(form.getExpiryDate().toString());
        // System.out.println("=====>"+date1);

        // } catch (ParseException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        return new MedicineDetailView(medicineRepository.save(new Medicine(form)));
    }

    @Override
    public MedicineDetailView get(Integer medicineId) throws NotFoundException {
        return medicineRepository.findByMedicineIdAndStatus(medicineId, Medicine.Status.ACTIVE.value)
                .map((medicine) -> {
                    return new MedicineDetailView(medicine);
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    public MedicineDetailView update(Integer medicineId, MedicineForm form) throws NotFoundException {
        return medicineRepository.findByMedicineIdAndStatus(medicineId, Medicine.Status.ACTIVE.value)
                .map((company) -> {
                    return new MedicineDetailView(medicineRepository.save(company.update(form)));
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Integer medicineId) throws NotFoundException {
        medicineRepository.deleteMedicine(medicineId);

    }
    @Override
    public Collection<MedicineListView> listExpired() {
        Date date = new Date();
        return medicineRepository.findByStatusAndExpiryDateLessThan(Medicine.Status.ACTIVE.value,date)
                .stream().map(x -> new MedicineListView(x)).collect(Collectors.toList());

    }


  
    @Override
	public Pager<MedicineListView> lists(Integer page, Integer limit, String sortBy,Boolean desc,String filter, String search) {

		if (!medicineRepository.findColumns().contains(sortBy)) {
			sortBy = "company_id";
		}

		if (page <= 0) {
			page = 1;
		}
		
		
			ArrayList<Byte> status = new ArrayList<>();
			// if (filter.equals("0")) {
			// 	status.add(Company.Status.DELETED.value);
			// } else if (filter.equals("1")) {
			// 	status.add(Company.Status.ACTIVE.value);
			// } else {
				
				status.add(Medicine.Status.DELETED.value);
				status.add(Medicine.Status.ACTIVE.value);
				
			// }
		

		Page<Medicine>medicine = medicineRepository.findAllByMedicineId(status, search,
				PageRequest.of(page - 1, limit, Sort.by(desc.booleanValue() ? Direction.DESC : Direction.ASC,
                        sortBy)));
		Pager<MedicineListView> medicineView = new Pager<>(limit, (int) medicine.getTotalElements(),
				page);

		medicineView
				.setResult(medicine.getContent().stream().map(MedicineDetailView::new).collect(Collectors.toList()));

		return medicineView;
	}

	}
