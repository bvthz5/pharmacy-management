package com.example.pharmacy.view;

import com.example.pharmacy.enitity.Company;

public class CompanyDetailView  extends CompanyListView {
	public CompanyDetailView(Company company) {
		super(
				company.getCompanyId(),
				company.getName(),
				company.getAddress(),
				company.getPhone(), 
				company.getDescription(),
				company.getType()
				
				);
	}
    
}