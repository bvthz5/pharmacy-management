package com.example.pharmacy.repository;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.example.pharmacy.enitity.Company;
import com.example.pharmacy.view.CompanyListView;

public interface CompanyRepository extends Repository<Company, Integer> {

    Collection<CompanyListView>findByStatus( byte value);

    Company save(Company company);

    Optional<Company>findByCompanyIdAndStatus(Integer company_id, byte value); 

    @Modifying
	@Query(value = "update company set status=0 where company_id=:companyId",nativeQuery = true)
	public void deleteCompany(@Param("companyId")Integer companyId);

    void deleteByCompanyId(Integer companyId);

     //Collection<CompanyListView> findAllByUserUserId(Integer userId);
	
	 Optional<Company>findByCompanyId(Integer companyId);

    
}