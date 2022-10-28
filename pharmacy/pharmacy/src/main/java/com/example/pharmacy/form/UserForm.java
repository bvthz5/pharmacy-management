package com.example.pharmacy.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserForm {

    private String name;
    @Email
    private String email;
    @Pattern(regexp = "^\\+?(\\d+ ?)*\\d$")
    private String phone;
    private String Password;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
