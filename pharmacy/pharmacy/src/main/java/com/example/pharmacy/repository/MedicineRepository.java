package com.example.pharmacy.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.example.pharmacy.enitity.Medicine;


public interface MedicineRepository extends Repository<Medicine, Integer>{

    //  Collection<MedicineListView>findByUserUserIdAndStatus(Integer currentUserId, byte value);
     Collection<Medicine>findByStatusAndQuantityGreaterThanAndExpiryDateGreaterThan( byte value,Integer quantity,Date currentDate);
     Collection<Medicine>findByStatusAndExpiryDateLessThan( byte value,Date currentDate);
    
	 Medicine save(Medicine medicine);

    Optional<Medicine>findByMedicineIdAndStatus(Integer medicine_id,  byte value); 

    @Modifying
	@Transactional
	@Query(value = "update medicine set status=0 where medicine_id=:medicineId",nativeQuery = true)
	public void deleteMedicine(@Param("medicineId")Integer medicineId);
    @Modifying
   	@Transactional
   	@Query(value = "update medicine set quantity=quantity-:quantity where medicine_id=:medicineId and quantity>=:quantity",nativeQuery = true)
   	public Integer editStock(Integer quantity,Integer medicineId);
     void deleteBymedicineId(Integer medicineId);

    // sCollection<MedicineListView> findByUserUserId(Integer userId);
	
	Optional<Medicine>findByMedicineId(Integer medicineId);
    @Modifying
	@Transactional
	@Query(value = "update medicine set status=3 where company_id=:companyId and status!=0",nativeQuery = true)
	public void deleteMedicineByCompany(@Param("companyId")Integer companyId);

	public void deleteByCompanyCompanyId(@Param("companyId")Integer companyId);

	@Modifying
	@Transactional
	@Query(value = "update medicine set status=1 where company_id=:companyId and status=3",nativeQuery = true)
	public void reactivateMedicineByCompany(@Param("companyId")Integer companyId);

	@Query(value = "SELECT `COLUMN_NAME`  FROM `INFORMATION_SCHEMA`.`COLUMNS`  WHERE `TABLE_NAME`='medicine'", nativeQuery = true)
	ArrayList<String> findColumns();

	@Query(value = "SELECT * FROM medicine  WHERE status IN ?1 AND (medicine_id  LIKE %?2% OR medicinename LIKE %?2% OR category  LIKE %?2% OR brand LIKE %?2% production_date  OR expiry_date  LIKE %?2%  OR medicinename LIKE %?2% quantity  OR cost_price LIKE %?2%)", nativeQuery = true)
	Page<Medicine> findAllByMedicineId(ArrayList<Byte> status, String search, Pageable page);

	@Query(value = "select * from medicine where status =1", nativeQuery = true)
    Collection<Medicine> findByStatus(byte value);



	@Query(value = "select * from medicine where status = 1 and quantity < 100", nativeQuery = true)

    Collection<Medicine> findByStatusAndQuantityLessThan(byte value, Integer quantity);




   

    
}