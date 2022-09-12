package com.example.pharmacy.view;

import com.example.pharmacy.enitity.Medicine;

public class MedicineDetailView extends MedicineListView{

    public MedicineDetailView(Medicine medicine) {
		super(
				medicine.getMedicineId(),
				medicine.getMedicinename(),
				medicine.getCategory(),
				medicine.getBrand(), 
				medicine.getProductionDate(),
				medicine.getExpiryDate(),
				// medicine.getDosage(),
				// medicine.getRegDate(),
				medicine.getQuantity(),
				medicine.getCostPrice(),
				medicine.getExpectedSale()


				
				);
	}
    
}