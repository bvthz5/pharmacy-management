package com.example.pharmacy.repository;

import java.util.Collection;
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
     Collection<Medicine>findByUserUserIdAndStatus(Integer currentUserId, byte value);
    Medicine save(Medicine medicine);

    Optional<Medicine>findByMedicineIdAndUserUserIdAndStatus(Integer medicine_id, Integer currentUserId, byte value); 

    @Modifying
	@Transactional
	@Query(value = "update medicine set status=0 where medicine_id=:medicineId",nativeQuery = true)
	public void deleteMedicine(@Param("medicineId")Integer medicineId);

    void deleteByUserUserId(Integer userId);

    // sCollection<MedicineListView> findByUserUserId(Integer userId);
	
	Optional<Medicine>findByMedicineIdAndUserUserId(Integer medicineId, Integer userId);

    
}