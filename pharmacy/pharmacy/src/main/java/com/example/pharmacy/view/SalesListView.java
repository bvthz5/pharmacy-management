package com.example.pharmacy.view;

import java.util.Date;

import org.springframework.data.domain.Sort.Order;

import com.example.pharmacy.enitity.Sales;

public class SalesListView {
    
    private final int salesId;
    private final int medicineId;
    private final String medicinename;
    private final String category;
    private final String brand;
    private final int userId;
    private final Date production_date;
    private final Date expiry_date;
    private final int salesQuantity;
    private final Date salesDate;
    private final Float totalAmount;
    
    public SalesListView(int salesId, int medicineId, String medicinename, String category, String brand,
            Date production_date, Date expiry_date, int salesQuantity, Date salesDate, Float totalAmount,int userId) {
        this.salesId = salesId;
        this.medicineId = medicineId;
        this.medicinename = medicinename;
        this.category = category;
        this.brand = brand;
        this.production_date = production_date;
        this.expiry_date = expiry_date;
        this.salesQuantity = salesQuantity;
        this.salesDate = salesDate;
        this.totalAmount = totalAmount;
        this.userId=userId;
    }

    public SalesListView(Sales x) {
        this.salesId = x.getSalesId() ;
        this.medicineId = x.getMedicine().getMedicineId();
        this.medicinename = x.getMedicine().getMedicinename();
        this.category = x.getMedicine().getCategory();
        this.brand = x.getMedicine().getBrand();
        this.production_date = x.getMedicine().getProductionDate();
        this.expiry_date = x.getMedicine().getExpiryDate();
        this.salesQuantity = x.getSalesQuantity();
        this.salesDate = x.getSalesDate();
        this.totalAmount = x.getTotalAmount();
        this.userId=x.getUser().getUserId();
    }

    public int getSalesId() {
        return salesId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public String getMedicinename() {
        return medicinename;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public Date getProduction_date() {
        return production_date;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public Date getSalesDate() {
        return salesDate;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public int getUserId() {
        return userId;
    }

    

}
