package com.example.pharmacy.service;

import javax.validation.Valid;

import org.springframework.validation.Errors;

import com.example.pharmacy.exception.BadRequestException;
import com.example.pharmacy.form.LoginForm;
import com.example.pharmacy.view.LoginView;
import com.example.pharmacy.view.UserView;

public interface UserService {
    LoginView login(@Valid LoginForm form, Errors errors) throws BadRequestException;

    LoginView refresh(String refreshToken) throws BadRequestException;

    UserView currentUser();

}
