package com.example.pharmacy.enitity;

import javax.persistence.Entity;
import javax.persistence.Id;

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

public User(Integer userId, String email, String password, String type) {
    this.userId = userId;
    this.email = email;
    this.password = password;
    this.type = type;

    
}

public User() {
}
public User(Integer userId) {
    this.userId = userId;
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

@Override
public String toString() {
    return "User [email=" + email + ", password=" + password + ", type=" + type + ", userId=" + userId + "]";
}

public String getImage() {
    return image;
}

public void setImage(String image) {
    this.image = image;
}
}
