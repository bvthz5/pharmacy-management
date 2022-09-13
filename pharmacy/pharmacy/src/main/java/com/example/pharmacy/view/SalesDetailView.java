package com.example.pharmacy.view;

import com.example.pharmacy.enitity.Sales;

public class SalesDetailView extends SalesListView{

    public SalesDetailView(Sales sales) {
        super(
            sales.getSalesId(),
            sales.getMedicine().getMedicineId(),
            sales.getMedicine().getMedicinename(),
            sales.getMedicine().getCategory(),
            sales.getMedicine().getBrand(),
            sales.getMedicine().getProductionDate(),
            sales.getMedicine().getExpiryDate(),
            sales.getSalesQuantity(),
            sales.getSalesDate(),
            sales.getTotalAmount()
            );
    }

    
    
}
