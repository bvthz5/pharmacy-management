package com.example.pharmacy.view;

import com.example.pharmacy.enitity.User;

public class UserView {
    private final int userId;
    private String email;
    private String type;

    public UserView(User user){
        this.userId=user.getUserId();
        this.email=user.getEmail();
        this.type=user.getType();
    }
    public int getUserId() {
        return userId;
    }
    public String getEmail() {
        return email;
    }
    public String getType() {
        return type;
    }
    
    

}
