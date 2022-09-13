package com.example.pharmacy.enitity;

import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import com.example.pharmacy.form.MedicineForm;

@Entity
public class Medicine {
    public static enum Status{
		DELETED((byte)0),
		ACTIVE((byte)1);
		public final byte value;
		private Status(byte value) {
		this.value= value;
	}
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer medicineId;
 private String medicinename;
private String category;
private String brand;
 private Date productionDate;
 private Date expiryDate;
//  private String dosage;
//  private String reg_date;
 private Integer quantity;
 private Float costPrice;
//  private String interest_rate;
//  private String expectedSale;

 private byte status;

// @ManyToOne(optional = false, fetch = FetchType.LAZY)
// private User user;

@OneToOne(mappedBy = "medicine", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
private Sales sales;

@ManyToOne(optional = false, fetch = FetchType.LAZY)
private Company company;


public Medicine() {
    
}
public Medicine(Integer medicineId) {
    this.medicineId=medicineId;
}

public Medicine(MedicineForm form) {
    // this.user=new User(userId);
    this.company=new Company( form.getCompanyId());
    this.medicinename=form.getMedicinename();
    this.category=form.getCategory();
    this.brand=form.getBrand();
    this.productionDate=form.getProductionDate();
    this.expiryDate=form.getExpiryDate();
    // this.dosage=form.getDosage();
    // this.reg_date=form.getRegDate();
    this.quantity=form.getQuantity();
    this.costPrice=form.getCostPrice();
    // this.interest_rate=form.getInterestRate();
    // this.expectedSale=form.getExpectedSale();
 
    this.status = Status.ACTIVE.value;
    
}



public Medicine update(MedicineForm form) {
    this.medicinename=form.getMedicinename();
    this.category=form.getCategory();
    this.brand=form.getBrand();
    this.productionDate=form.getProductionDate();
    this.expiryDate=form.getExpiryDate();
    // this.dosage=form.getDosage();
    // this.reg_date=form.getRegDate();    
    this.quantity=form.getQuantity();
    this.costPrice=form.getCostPrice();
    // this.interest_rate=form.getInterestRate();
    // this.expectedSale=form.getExpectedSale();
    return this;
}

public Company getCompany() {
    return company;
}
public void setCompany(Company company) {
    this.company = company;
}
public Integer getMedicineId() {
    return medicineId;
}
public void setMedicineId(Integer medicineId) {
    this.medicineId = medicineId;
}
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
public Integer getQuantity() {
    return quantity;
}
public void setQuantity(Integer quantity) {
    this.quantity = quantity;
}
public Float getCostPrice() {
    return costPrice;
}
public void setCostPrice(Float costPrice) {
    this.costPrice = costPrice;
}
// public String getExpectedSale() {
//     return expectedSale;
// }
// public void setExpectedSale(String expectedSale) {
//     this.expectedSale = expectedSale;
// }
public byte getStatus() {
    return status;
}
public void setStatus(byte status) {
    this.status = status;
}
// public User getUser() {
//     return user;
// }
// public void setUser(User user) {
//     this.user = user;
// }


    
}