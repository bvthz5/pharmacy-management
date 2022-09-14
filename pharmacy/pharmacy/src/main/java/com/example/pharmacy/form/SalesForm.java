package com.example.pharmacy.form;

import java.util.Date;

public class SalesForm {
    
    private Integer salesQuantity;
    private Date salesDate;
    private Float totalAmount;
    private  Integer companyId;
    private Integer medicineId;
    
    
    public Integer getSalesQuantity() 
    {
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
    public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public int getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

}
