package com.example.pharmacy.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.pharmacy.enitity.Company;
import com.example.pharmacy.exception.NotFoundException;
import com.example.pharmacy.extra.Pager;
import com.example.pharmacy.form.CompanyForm;
import com.example.pharmacy.repository.CompanyRepository;
import com.example.pharmacy.repository.MedicineRepository;
import com.example.pharmacy.security.util.SecurityUtil;
import com.example.pharmacy.service.CompanyService;
import com.example.pharmacy.view.CompanyDetailView;
import com.example.pharmacy.view.CompanyListView;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private MedicineRepository medicineRepository;


    @Override
    public Collection<CompanyListView> list(){
        return companyRepository.findByStatus(Company.Status.ACTIVE.value);
    }

    @Override
    public CompanyDetailView add(CompanyForm form){
        return new CompanyDetailView(companyRepository.save(new Company(form)));
    }

    @Override
    public CompanyDetailView get(Integer companyId) throws NotFoundException {
        return companyRepository.findByCompanyIdAndStatus( companyId, Company.Status.ACTIVE.value)
        .map((company)->{
            return new CompanyDetailView(company);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public CompanyDetailView update(Integer companyId, CompanyForm form) throws NotFoundException {
        return companyRepository.findByCompanyIdAndStatus( companyId, Company.Status.ACTIVE.value)
        .map((company) -> {
            return new CompanyDetailView(companyRepository.save(company.update(form)));
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public void delete(Integer companyId) throws NotFoundException {
        medicineRepository.deleteMedicineByCompany(companyId);
        companyRepository.deleteCompany(companyId);
       
        
    }
    // ----------------------------------------------------------------------------------------------

    @Override
	public Pager<CompanyListView> lists(Integer page, Integer limit, String sortBy,Boolean desc,String filter, String search) {

		if (!companyRepository.findColumns().contains(sortBy)) {
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
				
				status.add(Company.Status.DELETED.value);
				status.add(Company.Status.ACTIVE.value);
				
			// }
		

		Page<Company> company = companyRepository.findAllByCompanyId(status, search,
				PageRequest.of(page - 1, limit, Sort.by(desc.booleanValue() ? Direction.DESC : Direction.ASC,
                        sortBy)));
		Pager<CompanyListView> companyView = new Pager<>(limit, (int) company.getTotalElements(),
				page);

		companyView
				.setResult(company.getContent().stream().map(CompanyDetailView::new).collect(Collectors.toList()));

		return companyView;
	}


    @Override
	public long Count() {
		return companyRepository.countCompany();
	}


   




    
}