package com.example.pharmacy.service;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.http.HttpEntity;
import org.springframework.validation.Errors;

import com.example.pharmacy.enitity.User;
import com.example.pharmacy.exception.BadRequestException;
import com.example.pharmacy.exception.NotFoundException;
import com.example.pharmacy.form.ChangePasswordForm;
import com.example.pharmacy.form.ImageForm;
import com.example.pharmacy.form.LoginForm;
import com.example.pharmacy.view.LoginView;
import com.example.pharmacy.view.UserView;

public interface UserService {
    LoginView login(@Valid LoginForm form, Errors errors) throws BadRequestException;

    LoginView refresh(String refreshToken) throws BadRequestException;

    UserView currentUser();

    void forgotPassword(String token,String email);

    void resetPasswrd(String token, String password);

    UserView changePassword(@Valid ChangePasswordForm form) throws NotFoundException;

    UserView uploadPic(ImageForm form) throws Exception;

    

    long userCount();

    UserView detailView();

    byte[] getFileData();

  

}
