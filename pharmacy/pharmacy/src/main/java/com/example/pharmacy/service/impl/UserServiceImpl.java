package com.example.pharmacy.service.impl;

import static com.example.pharmacy.security.AccessTokenUserDetailsService.PURPOSE_ACCESS_TOKEN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.example.pharmacy.enitity.User;
import com.example.pharmacy.exception.BadRequestException;
import com.example.pharmacy.form.LoginForm;
import com.example.pharmacy.repository.UserRepository;
import com.example.pharmacy.security.config.SecurityConfig;
import com.example.pharmacy.security.util.TokenGenerator;
import com.example.pharmacy.security.util.TokenGenerator.Token;
import com.example.pharmacy.service.UserService;
import com.example.pharmacy.view.LoginView;

@Service
public class UserServiceImpl implements UserService{
    private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private SecurityConfig securityConfig;
    @Override
    public LoginView login(LoginForm form, Errors errors) throws BadRequestException {
        if (errors.hasErrors()) {
            throw badRequestException();
        }
        User user = userRepository.findByEmail(form.getEmail()).orElseThrow(UserServiceImpl::badRequestException);
        if (!form.getPassword() .equals(user.getPassword())) {
            throw badRequestException();
        }

        String id = String.format("%010d", user.getUserId());
        Token accessToken = tokenGenerator.create(PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry());
        Token refreshToken = tokenGenerator.create(PURPOSE_REFRESH_TOKEN, id + user.getPassword(), securityConfig.getRefreshTokenExpiry());
        return new LoginView(user, accessToken, refreshToken);
    }

    private static BadRequestException badRequestException() {
        return new BadRequestException("Invalid credentials");
    }
    
}
