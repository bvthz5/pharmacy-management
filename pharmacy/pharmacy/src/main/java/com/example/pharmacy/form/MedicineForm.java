package com.example.pharmacy.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MedicineForm {

    private String medicinename;
    private String category;
    private String brand;

    private Date productionDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date expiryDate;
    // private String dosage;
    // private String reg_date;
    private Integer quantity;
    private Float costPrice;
    // private String interest_rate;
    // private String expectedSale;
    private Integer companyId;
    
    public String getMedicinename() {
        return medicinename;
    }
    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
   
    
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
   
   
    public Date getProductionDate() {
        return productionDate;
    }
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
    public Date getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    // public String getExpectedSale() {
    //     return expectedSale;
    // }
    // public void setExpectedSale(String expectedSale) {
    //     this.expectedSale = expectedSale;
    // }
    public Float getCostPrice() {
        return costPrice;
    }
    public void setCostPrice(Float costPrice) {
        this.costPrice = costPrice;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
   
  

}