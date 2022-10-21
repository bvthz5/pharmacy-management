package com.example.pharmacy.view;

public class CompanyListView {

    private final int companyId;
    private final String name;
    private final String address;
    private final String phone;
    private final String description;
    private final byte status;
    // private final String type;

    public byte getStatus() {
        return status;
    }

    public int getCompanyId() {
        return companyId;
    }
 
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
 
    public String getDescription() {
        return description;
    }
 
    // public String getType() {
    //     return type;
    // }

    public CompanyListView(int companyId, String name, String address, String phone, String description, byte status) {
        this.companyId = companyId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.status= status;
        // this.type = type;

    }
}