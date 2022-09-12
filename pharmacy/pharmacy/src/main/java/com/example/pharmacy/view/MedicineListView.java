package com.example.pharmacy.view;

import java.util.Date;

import com.example.pharmacy.enitity.Medicine;

public class MedicineListView {

 private final int    medicineId;
 private final String medicinename;
private final String category;
 private final String brand;
 private final Date production_date;
 private final  Date expiry_date;
//  private final String dosage;
//  private final String reg_date;
 private final Integer quantity;
 private final Float cost_price;
//  private final String interest_rate;
 private final String expected_sale;
 
public MedicineListView(int medicineId, String medicinename, String category, String brand, Date production_date,
        Date expiry_date, Integer quantity, Float cost_price, String expected_sale) {
    this.medicineId = medicineId;
    this.medicinename = medicinename;
    this.category = category;
    this.brand = brand;
    this.production_date = production_date;
    this.expiry_date = expiry_date;
    this.quantity = quantity;
    this.cost_price = cost_price;
    this.expected_sale = expected_sale;
}
public MedicineListView(Medicine medicine) {
    this.medicineId = medicine.getMedicineId() ;
    this.medicinename = medicine.getMedicinename();
    this.category = medicine.getCategory();
    this.brand = medicine.getBrand();
    this.production_date = medicine.getProductionDate();
    this.expiry_date = medicine.getExpiryDate();
    this.quantity = medicine.getQuantity();
    this.cost_price = medicine.getCostPrice();
    this.expected_sale = medicine.getExpectedSale();
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
public Integer getQuantity() {
    return quantity;
}
public Float getCost_price() {
    return cost_price;
}
public String getExpected_sale() {
    return expected_sale;
}




    
}