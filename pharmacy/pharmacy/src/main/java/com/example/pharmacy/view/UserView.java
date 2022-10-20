package com.example.pharmacy.view;

import com.example.pharmacy.enitity.User;

public class UserView {
  
    private final int userId;
    private String name;
    private String email;
    private String type;
    private String image;


    public UserView(User user){
        this.userId=user.getUserId();
        this.email=user.getEmail();
        this.type=user.getType();
        this.name=user.getName();
        this.image=user.getImage();
        
    }
    public int getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public String getType() {
        return type;
    }
    public String getImage() {
        return image;
    }
    
    

}
