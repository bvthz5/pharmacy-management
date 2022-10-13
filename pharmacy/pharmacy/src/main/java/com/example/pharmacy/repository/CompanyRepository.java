package com.example.pharmacy.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Transactional
	@Query(value = "update company set status=0 where company_id=:companyId",nativeQuery = true)
	public void deleteCompany(@Param("companyId")Integer companyId);

    void deleteByCompanyId(Integer companyId);

     //Collection<CompanyListView> findAllByUserUserId(Integer userId);
	
	 Optional<Company>findByCompanyId(Integer companyId);

// ---------------------------------------------------------------------------------------------------------------------------
    //  The INFORMATION_SCHEMA.COLUMNS view allows you to get information about all columns for all tables and views within a database.

     @Query(value = "SELECT `COLUMN_NAME`  FROM `INFORMATION_SCHEMA`.`COLUMNS`  WHERE `TABLE_NAME`='company'", nativeQuery = true)
	ArrayList<String> findColumns();


    @Query(value = "SELECT * FROM company  WHERE status IN ?1 AND (company_id LIKE %?2% OR name LIKE %?2% OR description LIKE %?2% OR phone LIKE %?2%, OR address  LIKE %?2%  )", nativeQuery = true)
	Page<Company> findAllByCompanyId(ArrayList<Byte> status, Integer companyId, String search, Pageable page);

    @Query(value = "SELECT * FROM company WHERE status IN ?1 ", nativeQuery = true)
    long countCompany();

    
}