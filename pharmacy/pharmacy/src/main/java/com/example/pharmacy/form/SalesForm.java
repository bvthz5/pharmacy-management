package com.example.pharmacy.form;

import java.util.Date;

public class SalesForm {
    
    private Integer salesQuantity;
    private Date salesDate;
    private Float totalAmount;
    
    public Integer getSalesQuantity() {
        return salesQuantity;
    }
    public void setSalesQuantity(Integer salesQuantity) {
        this.salesQuantity = salesQuantity;
    }
    public Date getSalesDate() {
        return salesDate;
    }
    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }
    public Float getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

}
