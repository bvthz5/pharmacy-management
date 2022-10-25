package com.example.pharmacy.repository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.example.pharmacy.enitity.Medicine;
import com.example.pharmacy.view.MedicineListView;


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
	
	Collection<Medicine>findByMedicineId(Integer medicineId);

	
    @Modifying
	@Transactional
	@Query(value = "update medicine set status=0 where company_id=:companyId",nativeQuery = true)
	public void deleteMedicineByCompany(@Param("companyId")Integer companyId);

	@Query(value = "select * from medicine where status = 1 and quantity < 100", nativeQuery = true)
	Collection<Medicine> findByStatusAndQuantityLessThan(byte value, Integer quantity);

    
}