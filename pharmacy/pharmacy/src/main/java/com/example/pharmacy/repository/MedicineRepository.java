package com.example.pharmacy.repository;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.example.pharmacy.enitity.Medicine;


public interface MedicineRepository extends Repository<Medicine, Integer>{

    //  Collection<MedicineListView>findByUserUserIdAndStatus(Integer currentUserId, byte value);
     Collection<Medicine>findByStatusAndQuantityGreaterThan( byte value,Integer quantity);
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
	@Query(value = "update medicine set status=0 where company_id=:companyId",nativeQuery = true)
	public void deleteMedicineByCompany(@Param("companyId")Integer companyId);

    
}