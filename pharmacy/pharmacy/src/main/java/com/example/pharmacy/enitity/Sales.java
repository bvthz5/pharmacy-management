package com.example.pharmacy.enitity;

import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import com.example.pharmacy.form.SalesForm;

@Entity
public class Sales {

    public static enum Status {

        DELETED((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;

        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesId;
    private Integer salesQuantity;
    @Temporal(TemporalType.TIMESTAMP)
    private Date salesDate;
    private byte status;
    private Float totalAmount;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn
    private Medicine medicine;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Company company;

    public Sales() {
    }

    public Sales(Integer salesId) {
        this.salesId = salesId;
    }

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Sales(SalesForm form,Integer userId) {
                 this.user=new User(userId);
                this.medicine=new Medicine(form.getMedicineId());
                 this.company=new Company(form.getCompanyId());
        this.salesQuantity = form.getSalesQuantity();
        // this.salesDate = form.getSalesDate();
        // this.totalAmount = form.getTotalAmount();
        this.salesDate = new Date();
        this.totalAmount = form.getTotalAmount();
        // this.medicine = medicine;
        // this.user = user;
        // this.company = company;
        this.status = Status.ACTIVE.value;
    }


}
