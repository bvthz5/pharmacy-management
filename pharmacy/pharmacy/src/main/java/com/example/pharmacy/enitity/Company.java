package com.example.pharmacy.enitity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import com.example.pharmacy.form.CompanyForm;


@Entity
public class Company {
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
	private Integer companyId;
     private String name;
     private String address;
     private String phone;
     private String description;
    //  private String type;
     private byte status;
	
	
	// @ManyToOne(optional = false, fetch = FetchType.LAZY)
	// private User user;
	
	public Company() {
		
	}
	public Company(Integer companyId) {
		this.companyId=companyId;
	}
	
	public Company(CompanyForm form) {
		// this.user=new User(userId);
		this.name=form.getName();
		this.address=form.getAddress();
		this.phone=form.getPhone();
		this.description=form.getDescription();
		// this.type=form.getType();
		
		this.status = Status.ACTIVE.value;
		
	}
	
	
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer itemId) {
		this.companyId = itemId;
	}
    //
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    //

    public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    //
    public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    //
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    //
	// public String getType() {
	// 	return type;
	// }
	// public void setType(String type) {
	// 	this.type = type;
	// }
    //
	
	public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
	//
	// public User getUser() {
	// 	return user;
	// }
	// public void setUser(User user) {
	// 	this.user = user;
	// }
	public Company update(CompanyForm form) {
		this.name=form.getName();
		this.address=form.getAddress();
		this.phone=form.getPhone();
		this.description=form.getDescription();
		// this.type=form.getType();
		return this;
	}
}
