package com.example.pharmacy.enitity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.example.pharmacy.form.UserForm;

@Entity
public class User {
    @Id
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private String type;
    private String resetPasswrdToken;
    private String image;
    private String phone;

   

    public User(Integer userId, String name, String email, String password, String type, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.phone = phone;

    }

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, String image) {
        this.userId = userId;
        this.image = image;

    }

    public User update(Integer userId, String image) {
        this.userId = userId;
        this.image = image;
        return this;
    }

    public User updates(UserForm userForm, Integer userId) {
        this.userId = userId;
        this.email = userForm.getEmail();
        this.name = userForm.getName();
        this.phone = userForm.getPhone();
        this.password=userForm.getPassword();
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResetPasswrdToken() {
        return resetPasswrdToken;
    }

    public void setResetPasswrdToken(String resetPasswrdToken) {
        this.resetPasswrdToken = resetPasswrdToken;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", type="
                + type + ", resetPasswrdToken=" + resetPasswrdToken + ", image=" + image + ", phone=" + phone + "]";
    }

  

    public User get() {
        return this;
    }

}
