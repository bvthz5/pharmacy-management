package com.example.pharmacy.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.pharmacy.form.validation.Password;

/**
 *
 * @author nirmal
 */
public class LoginForm {

    @NotBlank
    @Size(max = 255)
    @Email
    private String email;
    @Password
    private String password;

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
}
